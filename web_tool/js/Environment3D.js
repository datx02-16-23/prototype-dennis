	
	
	var VisualizationEnvironment3D =  function(conatiner, browser){
		// orientation 
		this.prev_x = 0.0,
		this.prev_y = 0.0,
		this.x,
		this.y,
		this.mousedown,
		this.camera_theta = Math.PI / 4.0, 
		this.camera_phi = Math.PI / 6.0,
		this.camera_r = 75.0,

		// THREE.js
		this.renderer = null, 	
		this.scene = null, 			
		this.camera = null, 	
		this.raycaster = new THREE.Raycaster(),	
		

		this.ambient = 0xd1d1d1,
		this.specular = 0x212121,
		
		this.light, 
		this.ambientLight,
	
		// bounding volumes
		this.mouse = new THREE.Vector2(),
		
		// DOM
		this.conatiner = container,
		
		// browser specifics
		this.browser = browser,
		
		/*
			
		*/


		this.init = function(){
			this.browser = browser;
			this.container = container;
			/*
				renderer
			*/
			
			this.renderer = new THREE.WebGLRenderer( { clearColor: 0xf8f8f8, clearAlpha: 1, 
				antialiasing: true, pixelRatio: window.devicePixelRatio, alpha: true  } );
			this.renderer.setSize( this.CANVAS_WIDTH, this.CANVAS_HEIGHT );
			//this.renderer.setClearColor( 0xf8f8f8 );
			//this.DOM["container"].appendChild( this.renderer.domElement );
			conatiner.appendChild( this.renderer.domElement );
			/*
				scene
			*/
			
			this.scene = new THREE.Scene();
			
			/*
				lights
			*/
			
			// ambient
			this.ambientLight = new THREE.AmbientLight( this.ambient); // soft white light
			this.scene.add( this.ambientLight );
			
			// specular
			this.light = new THREE.PointLight( this.specular );
			this.light.position.set( 2, 20, 0 );
			this.scene.add(this.light);
			
			
			/*
				camera
			*/
			
			this.camera = new THREE.PerspectiveCamera(
			35, this.CANVAS_WIDTH / this.CANVAS_HEIGHT, 1, 10000);
			this.camera.position.set( -15, 10, 10 );
			this.camera.lookAt( this.scene.position );
			this.scene.add( this.camera );
			
			
			/*
				other
			*/
			
			this.renderer.clear(true, false,false);
			
			window.addEventListener( 'resize', this.onWindowResize, false );
			window['onresize'] = this.onWindowResize;
			this.onWindowResize();
		},
		
		this.onWindowResize = function() {
			
			this.CANVAS_WIDTH = window.innerWidth;
			this.CANVAS_HEIGHT= window.innerHeight;
			this.camera.aspect = (window.innerWidth*0.7)/ window.innerHeight;
			this.camera.updateProjectionMatrix();
			this.renderer.setSize( window.innerWidth*0.7, window.innerHeight );
			
			this.display();
		},
		
		
		/*
			refresh scene
		*/
		
		this.display = function(){
			
			//this.raycaster.setFromCamera(this.mouse, this.camera );
			//console.log("displ: "+this.mouse.x+", "+this.mouse.y);
			
		// calculate objects intersecting the picking ray
	/*
			var intersects = raycaster.intersectObjects( scene.children );
			for(var i = 0; i < intersects.length; i++){
				intersects[i].object.material.color.setHex(0x000000);
			}
		*/		
			var coords = this.sphericalToCartesian(this.camera_theta, this.camera_phi, this.camera_r);
			this.camera.position.x = coords[0];
			this.camera.position.y = coords[1] + 10;
			this.camera.position.z = coords[2];
			
			this.updateScene();
			this.light.position.set( coords[0],coords[1],coords[2]);
			
			this.camera.lookAt( this.scene.position );
			
			this.renderer.render( this.scene, this.camera );
			
		},
		
		this.updateScene = function(){
			//graph.update();
		},

		/*
			convert coordinates
		*/
		
		this.sphericalToCartesian = function(theta, phi, r){
			return [r * Math.sin(theta)*Math.sin(phi),
								r * Math.cos(phi), 
								r * Math.cos(theta)*Math.sin(phi) ];
		},
		
		
		
		/*
			mouse events
		*/
		
		this.mouseDown = function(evt){
			this.mousedown = true;
		},

		this.mouseUp = function(evt){
			
			this.mousedown = false;
			
			if(this.browser == "Firefox"){
				this.mouse.x=evt.clientX;
				this.mouse.y=evt.clientY;
			}else{
				this.mouse.x=window.event.clientX;
				this.mouse.y=window.event.clientY;
				btnType = window.event.button;
			}

			this.mouse.x = ((this.mouse.x/ window.innerWidth ) -0.5)*2;
			
			var y = ((this.mouse.y/ window.innerHeight)-0.5)*-2;
			this.mouse.y = (((this.mouse.y-20)/ window.innerHeight ) -0.5)*-2;
			
			this.display();
			
			return false; 
		},

		/*
			mouse motion
		*/
		
		this.motion = function(evt){
			
			var btnType;
			
			if(this.browser == "Firefox"){
				this.x=evt.clientX;
				this.y=evt.clientY;
			}else{
				this.x=window.event.clientX;
				this.y=window.event.clientY;
				btnType = window.event.button;
			}
			
			this.delta_x = this.x - this.prev_x;
			this.delta_y = this.y - this.prev_y;
			
			//alert(delta_x+", "+delta_y+", "+x+", "+y);
			
			
			if(this.mousedown && (btnType== 2))
			{
				this.camera_r -= this.delta_y * 0.3;
				// make sure cameraDistance does not become too small
				this.camera_r = Math.max(0.1, this.camera_r);
				this.display();
			}
			
			if(this.mousedown && (btnType == 0))
			{
				this.camera_phi	-= this.delta_y * 0.3 * Math.PI / 180.0;
				this.camera_phi = Math.min(Math.max(0.01, this.camera_phi), Math.PI - 0.01);
				this.camera_theta -= this.delta_x * 0.3 * Math.PI / 180.0;
				
				this.display();
			}
			
			this.prev_x = this.x;
			this.prev_y = this.y;
			//console.log(this.y + " "+ this.x);
			return false; 
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
	
	
	
	
	