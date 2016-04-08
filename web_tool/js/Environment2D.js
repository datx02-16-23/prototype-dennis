
var Environment2D =  function(args){
	
	this.container = args.container, 
	this.debugContainer = args.debugContainer,
	this.browser = args.browser,
	this.canvas,
	this.CANVAS_WIDTH,
	this.CANVAS_HEIGHT,
	
	
	this.init = function(){		
	
		this.canvas = document.createElement("canvas");
		container.appendChild(this.canvas);
		
		this.CANVAS_WIDTH = window.innerWidth;
		this.CANVAS_HEIGHT= window.innerHeight;
		this.canvas.style.width = this.CANVAS_WIDTH*0.7+"px";
		this.canvas.style.height = this.CANVAS_HEIGHT+"px";

		var context = this.canvas.getContext('2d');
		var centerX = this.canvas.width / 2;
		var centerY = this.canvas.height / 2;
		var radius = 70;
		
		context.beginPath();
		context.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
		context.fillStyle = 'green';
		context.fill();
		context.lineWidth = 5;
		context.strokeStyle = '#003300';
		context.stroke();
		
	}
}