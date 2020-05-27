function fundo(ctx)
{
	ctx.beginPath();
	ctx.fillStyle="blue";
	ctx.fillRect(0,0,innerWidth,innerHeight);
}


function circulo(ctx,x,y)
{
	let circle = document
				.getElementById("myCanvas")
				.getContext("2d");

	this.x=x;
	this.y=y;

	this.draw= function(){
		circle.beginPath();
		circle.fillStyle="black";
		circle.arc(this.x,this.y,10,0,2*Math.PI);
		circle.fill();
	}
	this.anim = function(){
		this.x+=2;
		this.draw();
	}
}


function main()
{
	let ctx= document
			.getElementById("myCanvas")
			.getContext("2d");
	let circulo1 = new circulo(ctx,100,100);

	function animate()
	{
		requestAnimationFrame(animate);
		fundo(ctx);
		circulo1.anim();
	}

	animate();
}