	
	
	var VisualizationEnvironment =  function(){
		
		// DOM
		this.DOM,
		
		// browser specifics
		this.browser,
		
		
		
		this.endLoad = function(){
			document.body.removeChild(this.DOM["spinner"]);
			//graphTest();
			//test();
		},
		
		this.setUpDOM = function(){
			
			this.DOM = [];
			
			this.CANVAS_WIDTH = window.innerWidth;
			this.CANVAS_HEIGHT= window.innerHeight - 50;
			this.getClientBrowser();
			
			// container window
			this.DOM["container_window"] = document.getElementById("container_window");
			this.DOM["container_window"].style.width = this.CANVAS_WIDTH+"px";
			this.DOM["container_window"].style.height = this.CANVAS_HEIGHT+"px";
			// container
			this.DOM["container"] = document.getElementById("container");
			
			/*
			if(this.browser == "firefox"){
				this.DOM["container"].addEventListener("mouseMove", this.motion, false);
				this.DOM["container"].addEventListener("mouseup", this.mouseUp, false);
				this.DOM["container"].addEventListener("mousedown", this.mouseDown, false);
			}
			*/
			// debug
			this.DOM["debug_container"] = document.getElementById("debug_container");
			// spinner
			var spinner = new Spinner();
			this.DOM["spinner"] = spinner.getSpinner("spinner");
			document.body.appendChild(this.DOM["spinner"]);
			// input text
			document.getElementById("text_input").value="";
			this.DOM["input_text_window"] = document.getElementById("input_text_window");
			this.DOM["container_window"].removeChild(this.DOM["input_text_window"]);
			
			// uplaod file window
			this.DOM["upload_file_window"] = document.getElementById("upload_file_window");
			this.DOM["upload_file_form"] = document.getElementById("upload_file_form");
			//this.DOM["container_window"].removeChild(this.DOM["upload_file_window"]);
			// data display
			this.DOM["data_display"] = document.getElementById("data_display");
			// player
			this.DOM["player"] = document.getElementById("player");
			this.DOM["container_window"].removeChild(this.DOM["player"]);
			// footer
			this.DOM["footer"] = document.getElementById("footer");
			var margin = this.CANVAS_HEIGHT + 50;
			this.DOM["footer"].style.marginTop = margin +"px";
			// 
			this.DOM["window_frame"] = document.createElement("div");
			this.DOM["window_frame"].setAttribute("class","fadein_half_class");
			this.DOM["window_frame"].style.zIndex = 2;
			this.DOM["window_frame"].id = "window_frame";
			
			window.addEventListener( 'resize', this.onWindowResize, false );
			window['onresize'] = this.onWindowResize;
			this.onWindowResize();

		},
		
		this.openFileWindow = function(){
			alert("ok");
			this.DOM["input_text_window"].style.zIndex = 3;
			this.DOM["container_window"].appendChild(this.DOM["input_text_window"]);
			this.DOM["container_window"].appendChild(this.DOM["window_frame"]);
		},
		
		this.closeFileWindow = function(){
			this.DOM["input_text_window"].style.zIndex = 0;
			this.DOM["container_window"].removeChild(this.DOM["input_text_window"]);
			this.DOM["container_window"].removeChild(this.DOM["window_frame"]);
		},
		
		/*
			change dimensions when browser window is rezised
		*/
		
		this.onWindowResize = function() {
			
			this.CANVAS_WIDTH = window.innerWidth;
			this.CANVAS_HEIGHT= window.innerHeight;
			
			this.DOM["container_window"].style.width = this.CANVAS_WIDTH+"px";
			this.DOM["container_window"].style.height = this.CANVAS_HEIGHT+"px";
			
			this.DOM["container"].style.width = (this.CANVAS_WIDTH*0.7)+"px";
			this.DOM["container"].style.height = this.CANVAS_HEIGHT+"px";
			
			this.DOM["debug_container"].style.width = (this.CANVAS_WIDTH*0.3)+"px";
			this.DOM["debug_container"].style.height = this.CANVAS_HEIGHT+"px";
			
		},
		
		
		this.displayData = function(str){
			this.DOM["data_display"].innerHTML = "<source>"+str+"</source>";
		},
		
		
		this.currentProgram,
		this.programs = {
			"bubble sort": 
			function(markup){
				var currentProgram;
				currentProgram = new BubbleSortVisualizer({markup: markup});
				currentProgram.init();
				currentProgram.sort();
				return currentProgram;
				//object["body"] = currentProgram.sequence.events;
			},
			"breadth first search":
			function(markup){
				var currentProgram;
				currentProgram = new BFSVisualizer({markup: markup});
				currentProgram.init();
				currentProgram.search();
				return currentProgram;
			},
			"3d grid":
			function(markup){
				var currentProgram;
				currentProgram = new gridVisualizer({markup: markup});
				currentProgram.init({size:10});
				return currentProgram;
			},
			"graph":
			function(args){
				var currentProgram;
				currentProgram = new GraphVisualizer({markup: args.markup, environment: args.environment});
				currentProgram.init({size:10});
				return currentProgram;
			}
		},
		
		this.parseText = function(text){
			
			
			this.DOM["upload_file_form"].appendChild(this.DOM["spinner"]);
		
			try{
				var object = JSON.parse(text);
			}catch(e){
				alert("could not parse text: "+e.message);
				this.DOM["input_text_window"].removeChild(this.DOM["spinner"]);
				return false;
			}
		
			
			// choose program
			var markup = object;
			this.currentProgram = this.programs[markup.header.visual]({markup: markup, environment: this});
			this.DOM["upload_file_form"].removeChild(this.DOM["spinner"]);
			this.DOM["container_window"].removeChild(this.DOM["upload_file_window"]);
			this.DOM["container_window"].appendChild(this.DOM["player"]);
			this.display();
		
		},
		
		this.runVisualization = function(){
			this.currentProgram.play();
		},
		
		this.pauseVisualization = function(){
			this.currentProgram.pause();
		},
		
		this.stopVisualization = function(){
			this.currentProgram.stop();
		},

		
		/*
			BROWSER INFO
		*/
		this.getClientBrowser = function(){
			var nAgt = navigator.userAgent;
			if (nAgt.indexOf("Firefox")!=-1){
				this.browser = "Firefox";
			}
			if(nAgt.toLowerCase().indexOf('chrome') > -1){
				this.browser = "Chrome";
			}
		}
	}
	
	
	
	
	