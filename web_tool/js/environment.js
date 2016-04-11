
var Environment = {
	maxSize : 5,
	
	calculateWidth : function(args){
		if(args.divisions == 2){
			return args.width/2;
		}else{
			var w = Math.ceil(this.divisions/2);
			if(w > 1 ){
				return args.width/w;
			}
		}
		return args.width;
		
	},
	
	calculateHeight : function(args){
		var h = Math.ceil(Math.log2(args.divisions));
			
		if(h > 1){
			return args.height/h;
		}
		return args.height;
		
	},
	
	calculateMargin : function(args){
		var pos = 0;
		var add = 0;
		
		if(pos == args.position){
			return {w:0,h:0};
		}
		
		var w = Math.floor(Math.sqrt(args.position));
		pos = w;
		for(var i =0; i < w; i++){
			if(pos == args.position){
				return {w:w,h:i};
			}
			pos++;
		}
		

		// lower half
		for(var h = 0; h < this.maxSize; h++){
			pos = h*2;
			if(pos == args.position){
				return {w:0,h:h};
			}
			for(var i =0; i < h; i++){
				if((pos + i)== args.position){
					return {w:i,h:h};
				}
			}
		}
		
		return {w:0,h:0};
	}
	
	
}