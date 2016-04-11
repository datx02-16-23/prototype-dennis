

var Sequence = function(){
	
	this.iteration = 0,
	//this.visualizer = args.visualizer,
	//this.markup = args.markup,
	this.markup,
	this.time,
	this.running = false,
	this.MILI_SECONDS = 500,
	this.visualizers = [],
	
	this.setMarkup = function(markup){
		this.markup = markup;
	}
	
	this.addVisualizer = function(visualizer){
		this.visualizers.push(visualizer);
	},
	
	this.display = function(operation){
		console.log("sequence: display: "+this.visualizers.length);
		for(var i = 0; i < this.visualizers.length; i++ ){
			this.visualizers[i].display(operation);
		}
	}
	
	this.append = function(event){
		console.log(event.op+", "+event.id+", "+event.x[0]);
		this.markup.body.push(event);
	},
	
	
	/*
	this.print = function(){
		for(var i = 0; i < this.markup.body.length; i++){
			console.log(this.markup.body[i].op+", "+
			this.markup.body[i].id+", "+this.markup.body[i].x[0]);
		}
	},*/
	
	
	this.slowDown =  function(times){
		this.MILI_SECONDS = MILI_SECONDS/times;
	},
	
	this.speedUp = function(times){
		
		MILI_SECONDS = MILI_SECONDS*times;
	},
	
	this.stop = function(){
		this.running = false;
		this.iteration = 0;
	},
	
	this.pause = function(){
		this.running = false;
	},
	
	this.play = function(){
		this.running = true;
		this.iterate();
	},
	
	this.iterate = function(){
		if(this.running){
			//this.visualizer.display(this.markup.body[this.iteration]);
			this.display(this.markup.body[this.iteration]);
			this.iteration ++;
			var _this = this;
			if(this.iteration < this.markup.body.length && this.running){
				this.time = setTimeout(function(){
					_this.iterate();
				}, this.MILI_SECONDS);
			}
		}
	}
}

//var sequence = new Sequence();

var Visualizer = {
	sequence: new Sequence(),
	checkId : function(ds1, ds2){
		return (ds1.identifier == ds2.identifier);
	},
	
	play : function(){
		console.log("play");
		this.sequence.play(this);
	},
	
	stop : function(){
		this.sequence.stop(this);
	},
	
	pause : function(){
		this.sequence.pause(this);
	}
	
}



