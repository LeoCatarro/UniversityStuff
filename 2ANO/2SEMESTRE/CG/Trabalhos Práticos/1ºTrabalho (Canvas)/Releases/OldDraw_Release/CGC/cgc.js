/*******************************************************************************************
					**FUNÇÕES PARA DEFINIR O BACKGROUND**
********************************************************************************************/
function wave(x,y)
{
	let wave = document.getElementById("acanvas").getContext("2d");
	let wave_gradient = wave.createLinearGradient(0,330,acanvas.width,480);
		wave_gradient.addColorStop(0.33,"#33ccff");
		wave_gradient.addColorStop(0.66,"#00ace6");

	this.x=x;
	this.y=y;
	let inicio=x;

	this.draw = function()
	{
		wave.beginPath();
			wave.fillStyle=wave_gradient;
			wave.moveTo(this.x,this.y);
			wave.lineTo(this.x+100,this.y);
			wave.quadraticCurveTo(this.x+10,this.y-40,this.x+25,this.y-100);
			wave.quadraticCurveTo(this.x+5,this.y-175,this.x-50,this.y);
			wave.lineTo(this.x,this.y);
		wave.fill();
	}
	this.update = function() 
	{
		if(this.x + 250 >= innerWidth)
		{
			this.x = inicio;
			this.x+=2;
			this.draw();
		}
		else
		{
			this.x+=2;
			this.draw();
		}		
	}
}

function background()
{
/****************************************************************************
								**SKY && SUN && CLOUDS && BIRDS**
****************************************************************************/
	let sky = document.getElementById("acanvas").getContext("2d");

	let sky_gradient = sky.createLinearGradient(0,0,acanvas.width, acanvas.height);
		sky_gradient.addColorStop(0.33,"#b3edff");
		sky_gradient.addColorStop(0.66,"#80e1ff");

	sky.beginPath();
		sky.fillStyle =sky_gradient;
		sky.fillRect(0, 0, acanvas.width, acanvas.height);

	let sun = document.getElementById("acanvas").getContext("2d");
	sun.beginPath();
		sun.fillStyle = "Gold";
		sun.arc(50, 50, 35, 0, 2 * Math.PI);
	sun.fill();
	
	let clouds = document.getElementById("acanvas").getContext("2d");

	let x_clouds = 50; 
	let y_clouds = 80;  
	let r_clouds = 15;

		clouds.beginPath();
			clouds.fillStyle = "white";
				clouds.arc(x_clouds, y_clouds, r_clouds, 0, 2*Math.PI);
				clouds.fill();
			clouds.beginPath();
				clouds.arc(x_clouds+20, y_clouds-10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+20, y_clouds+10, r_clouds, 0, 2*Math.PI);
				clouds.fill();

				clouds.arc(x_clouds+40, y_clouds-10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+40, y_clouds+10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
			clouds.beginPath();
				clouds.arc(x_clouds+60, y_clouds, r_clouds, 0, 2*Math.PI);
				clouds.fill();

		x_clouds = 270; y_clouds = 50;

		clouds.beginPath();
			clouds.arc(x_clouds, y_clouds, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+20, y_clouds-10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+20, y_clouds+10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+40, y_clouds-10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+40, y_clouds+10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+60, y_clouds, r_clouds, 0, 2*Math.PI);
				clouds.fill();

		x_clouds = 680;  y_clouds = 120;

		clouds.beginPath();
			clouds.arc(x_clouds, y_clouds, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+20, y_clouds-10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+20, y_clouds+10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+40, y_clouds-10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+40, y_clouds+10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+60, y_clouds, r_clouds, 0, 2*Math.PI);
				clouds.fill();

		x_clouds = 950; y_clouds = 75;

		clouds.beginPath();
			clouds.arc(x_clouds, y_clouds, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+20, y_clouds-10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+20, y_clouds+10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+40, y_clouds-10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
				clouds.beginPath();
				clouds.arc(x_clouds+40, y_clouds+10, r_clouds, 0, 2*Math.PI);
				clouds.fill();
		clouds.beginPath();
				clouds.arc(x_clouds+60, y_clouds, r_clouds, 0, 2*Math.PI);
				clouds.fill();


		let bird = document.getElementById("acanvas").getContext("2d");
		
		bird.beginPath();
			bird.strokeStyle="#666666";
			bird.lineWidth=1;
			bird.arc(590,30,10,0,Math.PI,true);
			bird.arc(570,30,10,0,Math.PI,true);
		bird.stroke();

		bird.beginPath();
			bird.strokeStyle="#333333";
			bird.lineWidth=2;
			bird.arc(890,50,10,0,Math.PI,true);
			bird.arc(870,50,10,0,Math.PI,true);
		bird.stroke();

		bird.beginPath();
			bird.strokeStyle="#262626";
			bird.lineWidth=3;
			bird.arc(840,100,20,0,Math.PI,true);
			bird.arc(800,100,20,0,Math.PI,true);
		bird.stroke();		
		bird.lineWidth=1;	
/****************************************************************************
								**MAR**
****************************************************************************/
	let mar = document.getElementById("acanvas").getContext("2d");
	let mar_gradient = mar.createLinearGradient(0,330,acanvas.width,480);
		mar_gradient.addColorStop(0.33,"#33ccff");
		mar_gradient.addColorStop(0.66,"#00ace6");

		mar.beginPath()
			mar.fillStyle=mar_gradient;
			mar.fillRect(0,330,acanvas.width,480);
		mar.closePath();
/****************************************************************************
								**PRAIA**
****************************************************************************/
	let praia = document.getElementById("acanvas").getContext("2d");
	let praia_gradient = praia.createLinearGradient(0,450,acanvas.width,acanvas.height);
		praia_gradient.addColorStop(0.33, "#f4ebca");
		praia_gradient.addColorStop(0.66, "#f5e9bd");
		praia_gradient.addColorStop(0.99, "#efdfaa");

	praia.beginPath()
		praia.fillStyle=praia_gradient;
		praia.fillRect(0,450,acanvas.width,acanvas.height);
	praia.closePath();				
/****************************************************************************
						**Chapéu de Sol && Toalhas && Outros**
****************************************************************************/

	let umbrella = document.getElementById("acanvas").getContext("2d");

		umbrella.beginPath();
			umbrella.strokeStyle="brown";
			umbrella.lineWidth = 6;
			umbrella.moveTo(900,500);
			umbrella.lineTo(900,378);
		umbrella.stroke();	

		umbrella.beginPath();
			umbrella.lineWidth = 1;
			umbrella.fillStyle="brown";
			umbrella.arc(910,430,10,Math.PI,2*Math.PI,true);
			umbrella.arc(930,430,10,Math.PI,2*Math.PI,true);
			umbrella.arc(950,430,10,Math.PI,2*Math.PI,true);
			umbrella.arc(970,430,10,Math.PI,2*Math.PI,true);
		umbrella.fill();
		umbrella.beginPath();
			umbrella.fillStyle="brown";
			umbrella.arc(830,430,10,Math.PI,2*Math.PI,true);
			umbrella.arc(850,430,10,Math.PI,2*Math.PI,true);
			umbrella.arc(870,430,10,Math.PI,2*Math.PI,true);
			umbrella.arc(890,430,10,Math.PI,2*Math.PI,true);	
		umbrella.fill();	
		umbrella.beginPath();
			umbrella.fillStyle="crimson";
			umbrella.moveTo(820,430);
			umbrella.quadraticCurveTo(830,380,900,380);
			umbrella.quadraticCurveTo(970,380,980,430);
		umbrella.fill();
		umbrella.lineWidth = 1;
//####################################################################
		umbrella.beginPath();
			umbrella.strokeStyle="brown";
			umbrella.lineWidth = 6;
			umbrella.moveTo(300,500);
			umbrella.lineTo(300,348);
		umbrella.stroke();	

		umbrella.beginPath();
			umbrella.lineWidth = 1;
			umbrella.fillStyle="brown";
			umbrella.arc(310,390,10,Math.PI,2*Math.PI,true);
			umbrella.arc(330,390,10,Math.PI,2*Math.PI,true);
			umbrella.arc(350,390,10,Math.PI,2*Math.PI,true);
			umbrella.arc(370,390,10,Math.PI,2*Math.PI,true);
		umbrella.fill();
		umbrella.beginPath();
			umbrella.fillStyle="brown";
			umbrella.arc(230,390,10,Math.PI,2*Math.PI,true);
			umbrella.arc(250,390,10,Math.PI,2*Math.PI,true);
			umbrella.arc(270,390,10,Math.PI,2*Math.PI,true);
			umbrella.arc(290,390,10,Math.PI,2*Math.PI,true);	
		umbrella.fill();	
		umbrella.beginPath();
			umbrella.fillStyle="crimson";
			umbrella.moveTo(220,390);
			umbrella.quadraticCurveTo(230,350,300,350);
			umbrella.quadraticCurveTo(380,350,380,390);
		umbrella.fill();
		umbrella.lineWidth = 1;

		let toalha = document.getElementById("acanvas").getContext("2d");

		toalha.beginPath();
			toalha.fillStyle="Seagreen";
			toalha.fillRect(250,470,40,80);
			toalha.fillRect(350,470,40,80);

			toalha.fillRect(950,470,40,80);

		
		let cesto = document.getElementById("acanvas").getContext("2d");
		
		cesto.beginPath();
			cesto.fillStyle="SaddleBrown";
			cesto.fillRect(830,480,60,20);
			cesto.strokeStyle="SaddleBrown";
			cesto.lineWidth=5;
			cesto.moveTo(860,480);
			cesto.lineTo(860,460);
		cesto.stroke();
		cesto.lineWidth=1;
}
/*******************************************************************************************
						**FUNÇÃO PARA DEFINIR O MODELO**
********************************************************************************************/
function head(x,y)  
{
	let h = document.getElementById("acanvas").getContext("2d");

	this.x=x;
	this.y=y;
	let inicio = x;

	this.draw = function()
	{
		h.beginPath();
		//Cabeça
			h.fillStyle = "pink";
			h.arc(this.x,this.y-30,18,0,2*Math.PI); 	

		//Nariz
			h.lineTo(this.x+21,this.y-30);
			h.lineTo(this.x+21,this.y-27);
			h.lineTo(this.x+15,this.y-25);
		h.fill();

		//Boca(linha reta)
		h.beginPath();	
			h.strokeStyle = "black";
			h.moveTo(this.x+16,this.y-21);
			h.lineTo(this.x+10,this.y-21);
		h.stroke();

		//Olho
		h.beginPath();
			h.fillStyle = "DeepSkyBlue";
			h.arc(this.x+12,this.y-32,3,0,2*Math.PI);
		h.fill();

		h.beginPath();
			h.fillStyle = "black";
			h.arc(this.x+12,this.y-32,1,0,2*Math.PI);
		h.fill();

		//Boné
		h.beginPath();
			h.strokeStyle = "#080808";
			h.fillStyle = "#080808";
			h.moveTo(this.x,this.y-40);
			h.lineTo(this.x+10, this.y-40);
			h.arc(this.x, this.y-40, 15, Math.PI, 2*Math.PI);
			h.lineTo(this.x-35, this.y-40);
		h.fill();
		h.stroke();

		//Pescoço
		h.beginPath()
			h.fillStyle = "pink";
			h.fillRect(this.x-3,this.y-20,8,10);
		h.fill();	
	}

	this.update = function()
	{
		if(this.x + 200 >= innerWidth)
		{
			this.x = inicio;
			this.x+=2;
			this.draw();
		}
		else
		{
			this.x+=2;
			this.draw();
		}
	}
}

function body(x,y) 
{
	let body = document.getElementById("acanvas").getContext("2d");

	this.x=x;
	this.y=y-10;
	let inicio = x;

	this.draw = function()
	{
		body.beginPath();
			body.fillStyle = "pink";
			body.lineTo(this.x-8, this.y);
			body.lineTo(this.x-8, this.y);
			body.lineTo(this.x-8, this.y+38);
			body.lineTo(this.x+8, this.y+38);
			body.lineTo(this.x+8, this.y);
			body.lineTo(this.x, this.y);
		body.fill();
		
		body.beginPath()
			body.strokeStyle="DarkSalmon"
			body.arc(this.x-4,this.y+12,1,0,2*Math.PI);
		body.stroke()
		body.beginPath()	
			body.arc(this.x+4,this.y+12,1,0,2*Math.PI);
		body.stroke()

		body.beginPath()
			body.moveTo(this.x-6,this.y+35);
			body.lineTo(this.x-1,this.y+35);
			body.moveTo(this.x+1,this.y+35);
			body.lineTo(this.x+6,this.y+35);

			body.moveTo(this.x-6,this.y+30);
			body.lineTo(this.x-1,this.y+30);
			body.moveTo(this.x+1,this.y+30);
			body.lineTo(this.x+6,this.y+30);

			body.moveTo(this.x-6,this.y+25);
			body.lineTo(this.x-1,this.y+25);
			body.moveTo(this.x+1,this.y+25);
			body.lineTo(this.x+6,this.y+25);
		body.stroke()
	}

	this.update = function()
	{
		if(this.x + 200 >= innerWidth)
		{
			this.x = inicio;
			this.x+=2;
			this.draw();
		}
		else
		{
			this.x+=2;
			this.draw();
		}
	}

}

function r_leg(x,y)
{
	let rLeg = document.getElementById("acanvas").getContext("2d");
	let rShoe = document.getElementById("acanvas").getContext("2d");

	this.x = x;
	this.y = y;
	let inicio = x;

	this.draw = function()
	{
		rLeg.beginPath();
		rLeg.fillStyle = "pink";
		rLeg.moveTo(this.x,this.y+28);
		rLeg.lineTo(this.x-8, this.y+28);
		rLeg.lineTo(this.x-8, this.y+66);
		rLeg.lineTo(this.x, this.y+66);
		rLeg.lineTo(this.x,this.y+28);
		rLeg.fill();
		//Calções
		rLeg.beginPath();
			rLeg.fillStyle = "black";
			rLeg.moveTo(this.x,this.y+28);
			rLeg.lineTo(this.x-8, this.y+28);
			rLeg.lineTo(this.x-8, this.y+44);
			rLeg.lineTo(this.x, this.y+44);
			rLeg.lineTo(this.x, this.y+28);
			rLeg.fill();
			
		rShoe.beginPath();
			rShoe.strokeStyle = "pink";
			rShoe.fillStyle = "pink";
			rShoe.ellipse(this.x-2,this.y+68,8,3,Math.PI,0,2*Math.PI);
		rShoe.stroke();
		rShoe.fill();
	}

	this.update = function()
	{
		if(this.x + 200 >= innerWidth)
		{
			this.x = inicio;
			this.x+=2;
			this.draw();
		}
		else
		{
			this.x+=2;
			this.draw();
		}
	}
	
}

function l_leg(x,y)
{
	let lLeg = document.getElementById("acanvas").getContext("2d");
	let lShoe = document.getElementById("acanvas").getContext("2d");

	this.x = x;
	this.y = y;
	let inicio = x;

	this.draw = function()
	{
		lLeg.beginPath();
			lLeg.fillStyle = "pink";
			lLeg.moveTo(this.x,this.y+28);	
			lLeg.lineTo(this.x+8,this.y+66);
			lLeg.lineTo(this.x+16,this.y+66);
			lLeg.lineTo(this.x+8,this.y+28);	
			lLeg.closePath();	
		lLeg.fill();
		//Calções
		lLeg.beginPath();
			lLeg.fillStyle = " black";
			lLeg.moveTo(this.x,this.y+28);
			lLeg.lineTo(this.x+3,this.y+43);
			lLeg.lineTo(this.x+11,this.y+43);
			lLeg.lineTo(this.x+8,this.y+28);
		lLeg.fill();
			

		lShoe.beginPath();
			lShoe.strokeStyle = "pink";
			lShoe.fillStyle = "pink";
			lShoe.ellipse(this.x+15,this.y+68,8,3,Math.PI,0,2*Math.PI);
		lShoe.fill();
	}

	this.update = function()
	{
		if(this.x + 200 >= innerWidth)
		{
			this.x = inicio;
			this.x+=2;
			this.draw();
		}
		else
		{
			this.x+=2;
			this.draw();
		}
	}
}

function r_arm(x,y)
{
	let rArm = document.getElementById("acanvas").getContext("2d");

	this.x = x;
	this.y = y;
	let inicio = x;

	this.draw = function()
	{
		rArm.beginPath();
			rArm.fillStyle = "pink";
			rArm.moveTo(this.x-8,this.y-10);
			rArm.lineTo(this.x-36,this.y+26);
			rArm.lineTo(this.x-28,this.y+30);
			rArm.lineTo(this.x-8,this.y);					
		rArm.fill();
	}

	this.update = function()
	{
		if(this.x + 200 >= innerWidth)
		{
			this.x = inicio;
			this.x+=2;
			this.draw();
		}
		else
		{
			this.x+=2;
			this.draw();
		}
	}
	
}

function l_arm(x,y)
{
	let lArm = document.getElementById("acanvas").getContext("2d");

	this.x = x;
	this.y = y-10;
	let inicio = x;

	this.draw = function()
	{
		lArm.beginPath();
			lArm.fillStyle = "pink";
			lArm.lineWidth = 1;
			lArm.moveTo(this.x+8,this.y);
			lArm.lineTo(this.x+26,this.y+40);
			lArm.lineTo(this.x+16,this.y+40);
			lArm.lineTo(this.x+8,this.y+16);			
		lArm.fill();	
	}

	this.update = function()
	{
		if(this.x + 200 >= innerWidth)
		{
			this.x = inicio;
			this.x+=2;
			this.draw();
		}
		else
		{	
			this.x+=2;
			this.draw();
		}
	}
	
}

function model(x,y)
{
	this.x=x;
	this.y=y;

	let cabeca = new head(this.x,this.y);

	let corpo = new body(this.x,this.y);

	let braco_d = new r_arm(this.x,this.y);
	let braco_e = new l_arm(this.x,this.y);

	let perna_d = new r_leg(this.x,this.y);
	let perna_l = new l_leg(this.x,this.y);	

	this.draw = function()
	{
		cabeca.draw();

		corpo.draw();

		braco_d.draw();
		braco_e.draw();

		perna_d.draw();
		perna_l.draw();		
	}

	this.update = function()
	{
		cabeca.update();

		corpo.update();
	
		braco_d.update();
		braco_e.update();

		perna_d.update();
		perna_l.update();	
	}		
}
/*******************************************************************************************
					**FUNÇÃO PARA DEFINIR O OBJETO COMPANHEIRO**
********************************************************************************************/
function object(x,y)
{
	this.x=x;
	this.y=y;
	let inicio = x;

	let prancha = document.getElementById("acanvas").getContext("2d");
	
	let prancha_gradient = prancha.createLinearGradient(200, 0, 860, 0);
		prancha_gradient.addColorStop(0.33, "#ff4d4d");
		prancha_gradient.addColorStop(0.66, "#ff1a1a");
	
	this.draw = function()
	{
		prancha.beginPath();
			prancha.fillStyle = prancha_gradient;
				prancha.moveTo(this.x,this.y);
				prancha.quadraticCurveTo(this.x,this.y-13,this.x+50,this.y-13);
				prancha.quadraticCurveTo(this.x+100,this.y-13,this.x+100,this.y);
				prancha.quadraticCurveTo(this.x+100,this.y+13,this.x+50,this.y+13);
				prancha.quadraticCurveTo(this.x,this.y+13,this.x,this.y);
		prancha.fill();	
		
		
	}

	this.update = function() 
	{
		if(this.x + 225 >= innerWidth)
		{
			this.x = inicio;
			this.x+=2;
			this.draw();
		}
		else
		{
			this.x+=2;
			this.draw();
		}
	}
}
/********************************************************************************************
									**MAIN**
*********************************************************************************************/
function main()
{
	//Definir Background	
	background();
	let onda = new wave(-155,330);
	
	//Definir o Modelo(pessoa)
	let modelo = new model(-100,235);

	//Definir Objeto Companheiro
	let objeto = new object(-130,305);
	
	//Função de Animação
	function animate()
    {
		requestAnimationFrame(animate);	
		background();
		objeto.update();
		modelo.update();
		onda.update();						
	}	
	//Animação
	animate();	
}