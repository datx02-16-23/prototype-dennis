
var VisualizationEnvironment2D =  function(){
	
	init = finction(canvas){
		context.beginPath();
		context.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
		context.fillStyle = 'green';
		context.fill();
		context.lineWidth = 5;
		context.strokeStyle = '#003300';
		context.stroke();
	}
}