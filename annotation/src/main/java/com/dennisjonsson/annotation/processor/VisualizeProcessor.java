package com.dennisjonsson.annotation.processor;


import com.dennisjonsson.annotation.processor.parser.TextProcessor;
import com.dennisjonsson.markup.DataStructure;
import com.dennisjonsson.markup.AbstractType;
import com.dennisjonsson.annotation.RunVisualization;
import com.dennisjonsson.annotation.VisualClassPath;
import com.dennisjonsson.annotation.Visualize;
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

import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.HashMap;


//@AutoService(Processor.class)
public class VisualizeProcessor extends AbstractProcessor {
	
	private Messager messager;
	private Filer filer;
	private HashMap<String, TextProcessor> sourceFiles;
        private HashMap<String, ArrayList<DataStructure>> looseDataStructures;
	private static final String PREFIX = "Visual";
        
        private static final String INSERTION_COMMENT = "/*end visualize*/";
	
	@Override
	public synchronized void init(ProcessingEnvironment env){
		messager = env.getMessager();		
		filer = env.getFiler();
		sourceFiles = new HashMap<String, TextProcessor>();
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
                
		for(TextProcessor processor : sourceFiles.values()){
			String sourceStr = null;
			String newClass = processor.getClassName() + PREFIX;
                        if(!processor.isWritten()){
                            processor.written();
                      	
                            // process new source file
                            processor.loadSource();
                            processor.removeAnnotations();
                            processor.renameClass(newClass);
                            processor.insertInterceptionCalls();
                            processor.insertInterceptorMethods(); 
                            processor.insertLogger();
                            processor.replace(INSERTION_COMMENT, "\nlogger.printLog();\n");
                            processor.writeSource();
  
				/*
				FileObject desination = filer.createSourceFile(
                                       // processor.getPackageElement().toString()+"."+
                                        processor.getClassName(), 
                                        processor.getPackageElement()
                                        
                                );*/
                            
			
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
                            TextProcessor sourceProcessor = sourceFiles.get(classElement.toString());
     
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
                            
                            //String source = readFile( path, className, unit);
                            
                            TextProcessor sourceProcessor = 
                                    new TextProcessor(
                                            className, 
                                            packageElement, 
                                            path);
                            
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
	
        /*

      
        */
        
        
      
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
         
       
*/
}