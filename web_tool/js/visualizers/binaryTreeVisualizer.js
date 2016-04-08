var BinaryTreeVisualizer = function(args){
	
	this.sequence,
	this.markup = args.markup,
	this.environment2d = args.environment,
	this.binaryTree,
	
	this.init = function(){
		
		this.environment2d.init();

		this.binaryTree = new Tree2d({
			arity: 2,
			treeHeight: 4,
			width: this.environment2d.CANVAS_WIDTH,
			height: this.environment2d.CANVAS_HEIGHT
		});

		// test

		for(var i =0; i < 10; i++){
			this.binaryTree.draw({
				index: i,
				color: "#334455",
				context: this.environment2d.context
			});
		}
		
		
	}
	
}
