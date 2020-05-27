function tabuleiro(c, cols, rows, color_1, color_2) {
	var xsize = 1.0 / cols;
	var ysize = 1.0 / rows;
	for (var i = 0; i < cols; i++) {
		for(var j = 0; j < rows; j++){
			if ( (i + j) % 2 == 0) {
				c.fillStyle = color_2;
			} else {
				c.fillStyle = color_1;
			}
			c.fillRect( i * xsize, j * ysize, xsize, ysize);
		}
	}
}

function main(){
	var c = document.getElementById("acanvas").getContext("2d");
	var nrow = 10;
	var ncol = 10;
	var color1 = "white";
	var color2 = "black";	
	c.scale(500, 500);
	tabuleiro(c, ncol, nrow, color1, color2);
}
