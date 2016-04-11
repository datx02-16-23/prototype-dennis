var StapleVisualizer = function(args){
	
	this.dataStructure = args.dataStructure,
	this.environment2d = args.environment,
	this.staples,
	
	this.init = function(){
	
		this.environment2d.init();

		this.staples = new Staples({
			width: this.environment2d.CANVAS_WIDTH,
			height: this.environment2d.CANVAS_HEIGHT
		});

		this.sequence.addVisualizer(this);
		

	},
	
	this.display = function(evt){
		//this.environment3d.displayData(evt.op+" <br>id: "+evt.id+"; <br>index: "+evt.index+"; <br>value: "+evt.value+"; ");
		if(evt.operation == "read"){
			this.read(evt.operationBody);
		}else if(evt.operation == "write"){
			this.write(evt.operationBody);
		}
		
		this.staples.update({
			context: this.environment2d.context
		});
	},
	
	this.write = function(opt){
		console.log("write");
		console.log(opt);
		var value = opt.value;
		if(value instanceof Array){
			for(var i = 0; i < value.length; i++){
				this.writeOperation(i, value[i]);
			}
		}else{
			this.writeOperation(opt.target.index[0], opt.value);
			
			if(opt.source != null){
				if(opt.source.index != null){
					this.read(opt);
				}
			}
		}
		
	},
	
	this.read = function(opt){
		var value = opt.value;
		console.log("read");
		console.log(opt);
		if(value instanceof Array){
			for(var i = 0; i < value.length; i++){
				this.readOperation(i, value[i]);
			}
		}else{
			this.readOperation(opt.source.index[0], value);
		}
	},
	
	
	this.readOperation = function(index, value){
		console.log("read "+value);
		this.staples.draw({
			index: index,
			color: "#4cec8f",
			value: value,
			context: this.environment2d.context
		});
	},
	
	
	this.writeOperation = function(index, value){
		console.log("write "+value);
		this.staples.draw({
			index: index,
			color: "#ff7d7d",
			value: value,
			context: this.environment2d.context
		});

	}
}

StapleVisualizer.prototype = Visualizer;