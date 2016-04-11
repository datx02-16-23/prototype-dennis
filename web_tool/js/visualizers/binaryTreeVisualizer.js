
var BinaryTreeVisualizer = function(args){
	
	this.dataStructure = args.dataStructure,
	this.environment2d = args.environment,
	this.binaryTree,
	
	this.init = function(){
	
		this.environment2d.init();
		var maxHeight = Math.ceil(Math.log2(this.dataStructure.attributes.size[0] + 1) - 1) + 1;
		
		this.binaryTree = new Tree2d({
			arity: 2,
			treeHeight: 0,
			width: this.environment2d.CANVAS_WIDTH,
			height: this.environment2d.CANVAS_HEIGHT
		});

		this.sequence.addVisualizer(this);
		

	},
	
	/*
	
		CONTROLLS
	*/
	
	
	this.display = function(evt){
		//this.environment3d.displayData(evt.op+" <br>id: "+evt.id+"; <br>index: "+evt.index+"; <br>value: "+evt.value+"; ");
		console.log("display");
		
		if(evt.operation == "read" 
			&& this.checkId(evt.operationBody.source, this.dataStructure)){
				
			this.read(evt.operationBody);
			
			this.binaryTree.update({
				context: this.environment2d.context
			});
			
		}else if(evt.operation == "write" 
			){
			
			this.write(evt.operationBody);
			
			this.binaryTree.update({
				context: this.environment2d.context
			});,
			
		}
		
		
	},
	
	
	
	this.write = function(opt){
		console.log("write");
		console.log(opt);
		var value = opt.value;
		if(value instanceof Array 
			&& this.checkId(opt.target, this.dataStructure)){
				
			for(var i = 0; i < value.length; i++){
				this.writeOperation(i, value[i]);
			}
			
		}else{
			
			if(this.checkId(opt.source, this.dataStructure)){
				this.writeOperation(opt.target.index[0], opt.value);
			}
			
			
			if(opt.source != null 
				&& this.checkId(opt.source, this.dataStructure)){
				if(opt.source.index != null){
					this.read(opt);
				}
			}
		}
		
	},
	
	this.read = function(opt){
		
		if(!this.checkId(opt.source, this.dataStructure)){
			return 0;
		}
		
		var value = opt.value;
		console.log("read");
		console.log(opt);
		
		if(value instanceof Array){
			
			for(var i = 0; i < value.length; i++){
				this.readOperation(i, value[i]);
			}
			
		}else{
			
			this.readOperation(opt.source.index[0]);
		}
		
	},
	
	
	this.readOperation = function(index, value){
		this.binaryTree.draw({
			index: index,
			color: "#4cec8f",
			value: value,
			context: this.environment2d.context
		});
	},
	
	
	this.writeOperation = function(index, value){
		this.binaryTree.draw({
			index: index,
			color: "#ff7d7d",
			value: value,
			context: this.environment2d.context
		});

	},
	
	this.testDraw = function(){

		// test
		for(var i =0; i < 100; i++){
			this.binaryTree.draw({
				index: i,
				color: "#334455",
				context: this.environment2d.context
			});
		}
	}
	
}

BinaryTreeVisualizer.prototype = Visualizer;

