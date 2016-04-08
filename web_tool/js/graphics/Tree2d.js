
var Tree2d = function(args){
	
	this.arity = args.arity,
	this.treeHeight = args.treeHeight,
	this.width = args.width,
	this.height = args.height,
	this.nodes = [],
	this.nodeSize,
	this.maxSize = Math.pow(this.arity, this.treeHeight + 1) - 1,

	this.addNode = function(i){

		var h = Math.ceil(Math.log2(i + 1) - 1); 
		var div = Math.pow(this.arity, h) + 1;
		var p = i - Math.pow(this.arity, h) + 1;

		this.nodes[i] = new TreeNode({
			position: i,
			h: h,
			p: p,
			div: div,
			color: '#003300',
			marked: false
			
		});
		return this.nodes[i];//return " "+i+"("+h+", "+div+", "+p+")";
	},

	this.test = function(){
		var str = "";
		for(var i =1; i < 20;i++){
			str += this.addNode(i);
		}
		alert(str);
	},

	this.draw = function(args){

		if(this.nodes[args.index] == null){
			this.addNode(args.index);
		}

		var size = Math.min(
			this.width/Math.pow(this.arity, this.treeHeight), 
			this.height/(this.treeHeight+1)
		);
		
		this.nodes[args.index].draw({
			size: size,
			width: this.width,
			height: this.height,
			treeHeight: this.treeHeight,
			context: args.context,
			color: args.color
		});
		
	}
	
}



var TreeNode = function(args){
	this.position = args.position,
	this.h = args.h,
	this.p = args.p,
	this.div = args.div,
	this.color = args.color,
	this.marked = args.marked,

	this.draw = function(args){

		var centerX = args.width/this.div*this.p;
		var centerY = args.height/args.treeHeight*this.h;
		var radius = args.size/2;
		var context = args.context;
		alert(centerX +" "+centerY+" "+radius);
		context.beginPath();
		context.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
		context.fillStyle = 'green';
		context.fill();
		context.lineWidth = 1;
		context.strokeStyle = '#003300';
		context.stroke();
	}

} 
