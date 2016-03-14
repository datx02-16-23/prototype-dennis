package com.dennisjonsson.annotation.processor;


import com.dennisjonsson.markup.DataStructure;
import com.dennisjonsson.markup.AbstractType;
import com.dennisjonsson.annotation.RunVisualization;
import com.dennisjonsson.annotation.VisualClassPath;
import com.dennisjonsson.annotation.Visualize;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Filer;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.ElementKind;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import javax.tools.FileObject;

import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.HashMap;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


//@AutoService(Processor.class)
public class VisualizeProcessor extends AbstractProcessor {
	
	private Messager messager;
	private Filer filer;
	private HashMap<String, SourceProcessor> sourceFiles;
        private HashMap<String, ArrayList<DataStructure>> looseDataStructures;
	private static final String PREFIX = "Visual";
        
        private static final String INSERTION_COMMENT = "/*end visualize*/";
	
	@Override
	public synchronized void init(ProcessingEnvironment env){
		messager = env.getMessager();		
		filer = env.getFiler();
		sourceFiles = new HashMap<String, SourceProcessor>();
                looseDataStructures = new HashMap<String, ArrayList<DataStructure>>();
	}

	/*
		process annot
	*/
	@Override
	public boolean process(Set<? extends TypeElement> annoations, RoundEnvironment env) { 
		
            
		messager.printMessage(Diagnostic.Kind.NOTE, "\n process visualize \n");
                processVisualClassPath(env);
		processVisualize(env);

		//messager.printMessage(Diagnostic.Kind.NOTE, "\n process RunVisualization \n");
		//processRunVisualization(env);
		
		/*
			go through all found classes containing annotations
		*/
                
		for(SourceProcessor processor : sourceFiles.values()){
			String sourceStr = null;
			String newClass = processor.getClassName() + PREFIX;
                        if(!processor.isWritten()){
                            processor.written();
                            try{	
				// process new source file
                                
				processor.removeAnnotations();
				processor.renameClass(newClass);
				processor.insertInterceptionCalls();
				processor.insertInterceptorMethods(); 
                                processor.insertLogger();
                                processor.replace(INSERTION_COMMENT, "\nlogger.printLog();\n");
                               // processor.removePackage();
				sourceStr = processor.getSource();
  
				 //write new source source
				FileObject desination = filer.createSourceFile(
                                       // processor.getPackageElement().toString()+"."+
                                        processor.getClassName(), 
                                        processor.getPackageElement()
                                        
                                );
                                createFile(processor.getPath()/*sourcePath*/,processor.getClassName(), sourceStr);
				//CreateSource(desination, sourceStr);
                            }catch(IOException e){
                                    throw new RuntimeException(e.toString());
                            }
			
                            messager.printMessage(Diagnostic.Kind.NOTE,
                                    "Source:\n"+sourceStr);

                            //sourceFiles.remove(processor.getClassName());
                        
                        }
			
		}
		
		
		return true;
	}
	
	/*
		processes visualization annotations
	*/
	private void processVisualize(RoundEnvironment env){
		
		for (Element annotatedElement : env.getElementsAnnotatedWith(Visualize.class)) {
			
			/*	
				Create representation of the annotated dataStructure
			*/
			Visualize annotation = (Visualize)annotatedElement.getAnnotation(Visualize.class);
			
			AbstractType abstractType = annotation.type();
			
			DataStructure dataStructure = 
				new DataStructure(
					abstractType.toString(),
					annotatedElement.asType().toString(),
                                        annotatedElement.toString()
				);
				
			/*
				get package and class names 
			*/ 
			Element classElement = getNextElementOf(annotatedElement, ElementKind.CLASS);
			Element packageElement = getNextElementOf(annotatedElement, ElementKind.PACKAGE);
			
			/* 
				check that an enclosing class was found 
			*/			
			if(classElement != null){	

			
                            /*
                                    Get or create new source File representation
                            */
                            SourceProcessor sourceProcessor = sourceFiles.get(classElement.toString());
     
                            if(sourceProcessor == null){
                                 if(!looseDataStructures.containsKey(classElement.toString())){
                                        
                                        ArrayList<DataStructure> list = new ArrayList<DataStructure>();
                                        looseDataStructures.put(classElement.toString(),list);
                                 //       throw new RuntimeException("class element: "+classElement.toString()+", source files: "+sourceFiles.keySet().size());
                                    }
                                    looseDataStructures.get(classElement.toString()).add(dataStructure);
                            }else{
                                sourceProcessor.addDataStructure(dataStructure);
                               // throw new RuntimeException("class element: "+classElement.toString()+", source files: "+sourceFiles.keySet().size()+". added datastructure!");
                            }
			//throw new RuntimeException(classElement.toString());	
			}else{
				// no enclosing class was found
				messager.printMessage(Diagnostic.Kind.ERROR,
					"enclosing class not found.");
				throw new RuntimeException("No enclosing class found for dataStructure");
			}

		}
	}
        
        private void processVisualClassPath(RoundEnvironment env){
		
		for (Element annotatedElement : 
                        env.getElementsAnnotatedWith(VisualClassPath.class)) {
                    
                        VisualClassPath annotation = 
                                (VisualClassPath)annotatedElement.getAnnotation(VisualClassPath.class);
			
                        String className = annotatedElement.getSimpleName().toString();
			String path = annotation.path().toString();
			Element packageElement = getNextElementOf(annotatedElement, ElementKind.PACKAGE);
                        
                        
			if(!(sourceFiles.containsKey(annotatedElement.toString()) || 
                                sourceFiles.containsKey(annotatedElement.toString()+PREFIX))){
                            CompilationUnit unit = null;
                            String source = readFile( path, className, unit);
                            
                            SourceProcessor sourceProcessor = 
                                    new SourceProcessor(
                                            source, 
                                            className, 
                                            packageElement, 
                                            path, 
                                            unit);
                            
                            // add loose dataStructures
                            if(looseDataStructures.containsKey(annotatedElement.toString())){
                                
                                sourceProcessor.getDataStructures()
                                        .addAll(looseDataStructures
                                                .get(annotatedElement.toString()));
                                
                            }
                            
                            sourceFiles.put(annotatedElement.toString(), sourceProcessor);
                            
                            //throw new RuntimeException(annotatedElement.toString());
                        }
                }
        }
   
	private void processRunVisualization(RoundEnvironment env){
		for (Element annotatedElement : env.getElementsAnnotatedWith(RunVisualization.class)) {
			messager.printMessage(Diagnostic.Kind.NOTE, "function: \n"+annotatedElement.toString());
		}
	}
	
	/*
		finds next parent Element of ElementKind kind from Element 'elm'  
	*/
	private Element getNextElementOf(Element elm, ElementKind kind){
		
		Element temp = null; 
		Element nextOuter = elm.getEnclosingElement();
		
		while(nextOuter != null && nextOuter.getKind() !=  kind){
				
				temp = nextOuter;
				nextOuter = temp.getEnclosingElement();
		}
		
		if(nextOuter.getKind() != kind ){
			return null;
		}

		return nextOuter;
	}
	
	private void printAllElements(Element top){
		
		ArrayList<Element> elements = new ArrayList<Element>();
		elements.addAll(top.getEnclosedElements());
		
		StringBuilder strBuilder = new StringBuilder();		
		
		while(elements.size() > 0){
			
			Element e = elements.get(elements.size()-1);
			strBuilder.append("\n"+e.getKind()+" "+e.getSimpleName()+" "+e.toString());
			elements.remove(elements.size()-1);
			elements.addAll(e.getEnclosedElements());
		}
		
		messager.printMessage(Diagnostic.Kind.NOTE, strBuilder.toString());
	}
	

        private void createFile(String path, String name,String source){
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(path+name+".java", "UTF-8");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(VisualizeProcessor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(VisualizeProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            writer.write(source);
            writer.close();
         
         
        } 
        
        /*
        String readFile(String path, String className){
            BufferedReader reader = null;
            try {
                reader = Files.newBufferedReader(Paths.get(path+className+".java"));
            } catch (IOException ex) {
                Logger.getLogger(VisualizeProcessor.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new RuntimeException(ex.getMessage());
            }
            
            Scanner scanner = new Scanner(reader);
            StringBuilder builder = new StringBuilder();
            while(scanner.hasNext()){
                builder.append(scanner.nextLine());
                builder.append("\n");
            }

            return builder.toString();
        }
        */
        
        InputStream getInputStream(String path, String className){
            InputStream stream = null;
            try {
                
                stream = 
                        Files.newInputStream(
                                Paths.get(path+className+".java"),  
                                StandardOpenOption.READ);
                
            } catch (IOException ex) {
                Logger.getLogger(VisualizeProcessor.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new RuntimeException(ex.getMessage());
            }
            
            return stream;
        }
        
        String readFile(String path, String className, CompilationUnit nunit){
            
            InputStream stream = null;
            StringBuilder builder = null;
            
            try {
                
                stream = getInputStream(path, className);
                
                Scanner scanner = new Scanner(stream);
                builder = new StringBuilder();
                while(scanner.hasNext()){
                    builder.append(scanner.nextLine());
                    builder.append("\n");
                }
                
                nunit = JavaParser.parse(stream);
                
            }catch (ParseException ex) {
                Logger.getLogger(VisualizeProcessor.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex.getMessage());
            }finally{
                if(stream != null){
                    try {
                        stream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(VisualizeProcessor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
            

            return builder.toString();
        }
        
        
      
	@Override
	public Set<String> getSupportedAnnotationTypes() { 
		Set<String> set = new LinkedHashSet<String>();
		set.add(Visualize.class.getCanonicalName());
		set.add(RunVisualization.class.getCanonicalName());
		return set;
	}

	@Override
	public SourceVersion getSupportedSourceVersion() { 
		return SourceVersion.latestSupported();
	}
        
         
        /*
         
         private void CreateSource(FileObject destination, String source){
		
		try{
			
			Writer wrt = destination.openWriter();
			wrt.write(source);
			wrt.close();
		}catch(IOException e){
			throw new RuntimeException(e.toString());
		}
				
	}
         
         private FileObject readSource(String className, String pkgName){
		FileObject fileObject = null;
              
		try{
  
			fileObject = filer.getResource(
                               
                               new JavaFileManager.Location(){
                                    @Override
                                    public String getName() {
                                        return Paths.get("C:/Users/dennis/Documents/" +
                                                "NetBeansProjects/" +
                                                "graphVisualizationTest/" +
                                                 "src/main/java/com/"
                                                + "dennisjonsson/"
                                                + "graphvisualizationtest/").toString();
                                        
                                    }

                                    @Override
                                    public boolean isOutputLocation() {
                                        return false;
                                    }
                                }
                                , 
				"",
				className+".java" ); 
					
		}catch(IOException e){
			throw new RuntimeException(e.toString());
		}
		return fileObject;
	}
*/
}