
var GraphVisualizer = function(args){
	
	this.graph,
	this.variables,
	this.sequence,
	this.markup = args.markup,
	this.environment = args.environment,
	this.arrayElements2d,
	this.writeColor3d = 0xff6d6d,
	this.writeColor2d = "#FF6D6D",
	this.readColor3d = 0x4ab23a,
	this.readColor2d = "#4ab23a",
	
	/*
			INIT
	*/
	
	this.init = function(){
		
		this.arrayElements2d = [];
		
		if(this.markup.header.variables[0] == null){
			alert("Graph program: no datastructure defined!");;
		}
		var type = this.markup.header.variables[0].type;
		
		// we only have this now 
		if(type != "ADJECENCY_LIST" && type != "ADJECENCY_MATRIX" ){
			alert("Graph program: could not find apropriate datastructure");
		}
		
		// create 2d DOM elements of data structures
		for(var i = 0; i <this.markup.header.variables.length; i ++){
			
			var arrayElement = new ArrayElement({
				lengthX: this.markup.header.variables[i].size[0], 
				lengthY: this.markup.header.variables[i].size[1],
				id: this.markup.header.variables[i].name });
				
			arrayElement.init();
			
			this.arrayElements2d[arrayElement.id] = arrayElement;
			
			this.environment.DOM["debug_container"].appendChild(arrayElement.DOM);
			
		}
		
		
		// create graph and insert nodes 
		this.graph = new Graph({environment: this.environment});
		this.graph.init();
		console.log("scene: "+this.environment.scene);
		console.log(this.markup.header.variables[0].size[0]);
		for(var i = 0; i < this.markup.header.variables[0].size[0]; i ++){
			
			var hex = 0x5fcbff;
			var sphereGeometry = new THREE.SphereGeometry( 1, 20, 20 );
			var material = new THREE.MeshLambertMaterial({ color: hex});
			var sphere = new THREE.Mesh(sphereGeometry, material);
			var pos = 20;
			
			sphere.position.set(pos*Math.random() - pos*Math.random(),
								 pos*Math.random() - pos*Math.random(),
								 pos*Math.random() - pos*Math.random());
			
			var hex = 0x000000;

			/*
			var bbox = new THREE.BoundingBoxHelper( sphere, hex );
			bbox.update();
			scene.add( bbox );
				*/				 
			this.graph.add({object: sphere});
			
		}
		
		this.graph.positionNodes();
		this.sequence = new Sequence({markup: this.markup, visualizer: this});
		
	},
	
	/*
	
		CONTROLLS
	*/
	
	this.play = function(){
		this.sequence.play(this);
	},
	
	this.stop = function(){
		this.sequence.stop(this);
	},
	
	this.pause = function(){
		this.sequence.pause(this);
	},
	
	this.display = function(evt){
		this.environment.displayData(evt.op+" <br>id: "+evt.id+"; <br>index: "+evt.index+"; <br>value: "+evt.value+"; ");
		if(evt.op == "read"){
			this.read(evt);
		}else if(evt.op == "write"){
			this.write(evt);
		}
	},
	
	
	
	/*
		OPERATIONS
	*/
	
	
	/*
		ADJECENCY MATRIX
	*/
	
	
	this.matrix = {};
	this.read = function(evt){
		
		this.arrayElements2d[evt.id].clearMarked();
		this.graph.clearMarked();
		
		if(evt.index.length == 1){
			// 1 index (node), traverse single node
			
			this.graph.traverse({object: this.graph.nodes[evt.index[0]], color: this.readColor3d});
			this.arrayElements2d[evt.id].markIndex({x: evt.index[0], color: this.readColor2d});
			
		}else if(evt.index.length == 2){
			// 2 indexes (nodes), traverse edge
			// connect the objects if not connected
			if(this.graph.nodes[evt.index[0]].graph.adjecent[evt.index[1]] == null){
				//this.connectNodes(evt);
				this.graph.mark({objects: [this.graph.nodes[evt.index[0]], this.graph.nodes[evt.index[1]]], color: this.readColor3d});
				this.arrayElements2d[evt.id].markIndex({x: evt.index[0], y: evt.index[1], color: this.readColor2d});
				this.arrayElements2d[evt.id].markCell({x:evt.index[0], y: evt.index[1], color: this.readColor2d});
			}else{
			
			// fetch edge
				var edgeObj = this.graph.nodes[evt.index[0]].graph.adjecent[evt.index[1]];
				var node2 = edgeObj.edge.value;
				this.graph.colorObject({object: edgeObj, color: 0x000000});
				this.graph.mark({objects: [edgeObj, this.graph.nodes[evt.index[0]], this.graph.nodes[evt.index[1]]], color: this.readColor3d});
				this.arrayElements2d[evt.id].markIndex({x: evt.index[0], y: evt.index[1], color: this.readColor2d});
				this.arrayElements2d[evt.id].markCell({x:evt.index[0], y: evt.index[1], color: this.readColor2d});
			}
			
		}
		this.environment.display();
	},
	
	/*
		write operations are passed to this function
	*/
	
	this.write = function(evt){
		
		this.arrayElements2d[evt.id].clearMarked();
		this.graph.clearMarked();
		
		//console.log("writing: obj1"+evt.index[0]+"obj2: "+evt.value[0]+", index: "+evt.index[1]);
		
		if(evt.index.length == 2){
			// connect two nodes
			
			this.connectNodes(evt);
		}
		
		this.environment.display();
	},
	
	this.connectNodes = function(evt){
		
		console.log("connect: "+evt.index[0] +", "+ evt.index[1]+", length:"+evt.index.length);
		var edge = this.graph.connectNodes({id1:evt.index[0] ,  id2: evt.index[1], index: evt.index[1], value: evt.value[0]});
		
		this.arrayElements2d[evt.id].markCell({x: evt.index[0], y:evt.index[1], color: this.writeColor2d});
		this.arrayElements2d[evt.id].markIndex({x:evt.index[0], y: evt.index[1], color: this.writeColor2d});
		this.arrayElements2d[evt.id].insertValue({x: evt.index[0], y: evt.index[1], value: evt.value[0]});
		this.graph.mark({objects: [edge, this.graph.nodes[evt.index[0]], this.graph.nodes[evt.index[1]]], color: this.writeColor3d});
		
	},
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	/* ------------------------------------------------------------------------------------------------------------------------------------------------- */
	/*
		ADJECENCY LIST
	*/
	this.list = {};
	/*
		read operations are passed to this function
	*/
	
	this.list.read = function(evt){
		
		this.arrayElements2d[evt.id].clearMarked();
		this.graph.clearMarked();
		
		if(evt.index.length == 1){
			// 1 index (node), traverse single node
			
			this.graph.traverse({object: this.graph.nodes[evt.index[0]], color: this.readColor3d});
			this.arrayElements2d[evt.id].markIndex({x: evt.index[0], color: this.readColor2d});
			
		}else if(evt.index.length == 2){
			// 2 indexes (nodes), traverse edge
			
			// connect the objects if not connected
			if(this.graph.nodes[evt.index[0]].graph.adjecent.length <=  evt.index[1] || 
				this.graph.nodes[evt.index[0]].graph.adjecent[evt.index[1]] == null){
				
				this.connectNodes(evt);
				
			}
			
			// fetch edge
			var edgeObj = this.graph.nodes[evt.index[0]].graph.adjecent[evt.index[1]];
			console.log("reading edge from: "+evt.index[1]);
			var node2 = edgeObj.edge.value;
			
			//this.graph.markEdge({edge:edgeObj, color: 0x00000});
			//this.graph.traverse({object: this.graph.nodes[evt.index[0]], color: this.readColor3d});
			this.graph.colorObject({object: edgeObj, color: 0x000000});
			this.graph.mark({objects: [edgeObj, this.graph.nodes[evt.index[0]], this.graph.nodes[evt.value[0]]], color: this.readColor3d});
			
			this.arrayElements2d[evt.id].markIndex({x: evt.index[0], y: evt.index[1], color: this.readColor2d});
			this.arrayElements2d[evt.id].markCell({x:evt.index[0], y: evt.index[1], color: this.readColor2d});
			
		}
		this.environment.display();
	},
	
	/*
		write operations are passed to this function
	*/
	
	this.list.write = function(evt){
		
		this.arrayElements2d[evt.id].clearMarked();
		this.graph.clearMarked();
		
		//console.log("writing: obj1"+evt.index[0]+"obj2: "+evt.value[0]+", index: "+evt.index[1]);
		
		if(evt.index.length == 2){
			// connect two nodes
			
			this.connectNodes(evt);
		}
		
		this.environment.display();
	},
	
	this.list.connectNodes = function(evt){
		
		var edge = this.graph.connectNodes({id1:evt.index[0] ,  id2:evt.value[0], index: evt.index[1] });
		this.arrayElements2d[evt.id].markCell({x: evt.index[0], y:evt.index[1], color: this.writeColor2d});
		this.arrayElements2d[evt.id].markIndex({x:evt.index[0], y: evt.index[1], color: this.writeColor2d});
		this.arrayElements2d[evt.id].insertValue({x: evt.index[0], y: evt.index[1], value: evt.value[0]});
		
		this.graph.mark({objects: [edge, this.graph.nodes[evt.index[0]], this.graph.nodes[evt.value[0]]], color: this.writeColor3d});
	}
	
	
}