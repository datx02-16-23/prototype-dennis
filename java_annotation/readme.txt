
-------------------------------------
Usage:

example:

@VisualClassPath(path = "computer/path/to/source.java")
class someClass{
..

	field variables (Arrays [1..n] for now)

	@Visualize(type=AbstractType.ARRAY)
    public static int[] b = new int[10];
	
    @Visualize(type=AbstractType.ADJECNECY_MATRIX)
    public static int[][] c = new int[10][10];
	
	...
	
	public static void main(String [] args){
		
		do stuff ..
		
		This comment is replaced by a printing function call in the generated file.
		(Will do it differently later)
		/*end visualize*/

	}
}

-------------------------------------
Note:
At the moment only binary expressions, assignments, and declarations 
where annoteted variables occur will be affected.

If all works, new files will be generated in the same 
folder as your souce files, i.e. in the the path you 
have given in @VisualClassPath

------------------------------------

maven:

copy paste into pom file of your own project:
see pom-file in tests.

...
<dependencies>
         
        <dependency>
            <groupId>com.dennisjonsson</groupId>
            <artifactId>annotation</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
     
    </dependencies>
    
  
    <build>
       
        <plugins>

          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.0</version>
            <configuration>
                <debug>true</debug>
              <annotationProcessors>
                <annotationProcessor>
                    com.dennisjonsson.annotation.processor.VisualizeProcessor
                  </annotationProcessor>
              </annotationProcessors>
              <source>1.8</source>
              <target>1.8</target>
              <compilerArguments>
                <AaddGeneratedAnnotation>true</AaddGeneratedAnnotation>
                <Adebug>true</Adebug>
              </compilerArguments>
            </configuration>
           <goals>
            <goal>-e</goal>
          </goals>
            <dependencies>
              <dependency>
                <groupId>com.dennisjonsson.annotationprocessor</groupId>
                <artifactId>annotationprocessor</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency> 
            </dependencies>
          </plugin>

        </plugins>
    </build>
	
-------------------------------------