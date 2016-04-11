
var Environment2D =  function(args){
	
	this.container = args.container, 
	this.debugContainer = args.debugContainer,
	this.browser = args.browser,
	this.divisions = args.divisions,
	this.position = args.position,
	this.canvas,
	this.CANVAS_WIDTH,
	this.CANVAS_HEIGHT,
	this.context,
	this.environmentContainer,
	this.margins,
	
	this.init = function(){
		
		this.environmentContainer = document.createElement("div");
		this.environmentContainer.style.position = "absolute";

		this.canvas = document.createElement("canvas");
		this.CANVAS_WIDTH = window.innerWidth;
		this.CANVAS_HEIGHT= window.innerHeight;
		
		this.CANVAS_HEIGHT =  this.calculateHeight({height: this.CANVAS_HEIGHT, divisions: this.divisions});
		this.CANVAS_WIDTH =  this.calculateWidth({width: this.CANVAS_WIDTH, divisions: this.divisions});
		
		this.margins = this.calculateMargin({position:this.position});
		
		console.log(this.position+": "+this.margins.w+", "+this.margins.h);
		this.environmentContainer.style.marginLeft = (this.margins.w*this.CANVAS_WIDTH)+"px";
		this.environmentContainer.style.marginTop = (this.margins.h*this.CANVAS_HEIGHT)+"px";

		this.environmentContainer.style.width = this.CANVAS_WIDTH+"px";
		this.environmentContainer.style.height = this.CANVAS_HEIGHT+"px";
		
		this.canvas.width = this.CANVAS_WIDTH;
		this.canvas.height = this.CANVAS_HEIGHT;
		this.canvas.style.width = this.CANVAS_WIDTH+"px";
		this.canvas.style.height = this.CANVAS_HEIGHT+"px";
		
		this.environmentContainer.appendChild(this.canvas);
		this.context = this.canvas.getContext('2d');

		this.container.appendChild(this.environmentContainer);
	
		
	}
}

Environment2D.prototype = Environment;
