/*##########################################################################################################################################################################
													EM FALTA:
														-->MELHORAR OBJETO COMPANHEIRO
														-->DETALHAR MODELO
														-->MELHORAR FUNDO
														-->CORRIGIR NUVENS
###########################################################################################################################################################################*/
/*******************************************************************************************
					**FUNÇÃO PARA DEFINIR O BACKGROUND**
********************************************************************************************/
function background_draw()
{
/****************************************************************************
								**SKY && SUN && CLOUDS**
****************************************************************************/
	let sky = document.getElementById("acanvas").getContext("2d");
	sky.beginPath();
		sky.fillStyle = "LightSkyBlue";
		sky.fillRect(0, 0, acanvas.width, acanvas.height);

	let sun = document.getElementById("acanvas").getContext("2d");
	sun.beginPath();
		sun.fillStyle = "yellow";
		sun.arc(50, 50, 35, 0, 2 * Math.PI);
	sun.fill();
	
	let clouds = document.getElementById("acanvas").getContext("2d");

	//let x_clouds = [150, 280, 680, 950]; let y_clouds = [180, 50, 150, 75];

	let x_clouds = 30; 
	let y_clouds = 180;  
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

		x_clouds = 680;  y_clouds = 150;

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

/****************************************************************************
								**STREET**
****************************************************************************/
	let street = document.getElementById("acanvas").getContext("2d");
	street.beginPath();	
		
		street.fillStyle = "SlateGray";
		street.fillRect(0,  412, innerWidth, innerHeight);
		
		street.moveTo(0,465);
		street.strokeStyle = "white";
		street.lineWidth = 4;
		let x_street = 0;

		for(let i=0 ; i<innerWidth ; i++)
		{
			street.lineTo(x_street+50, 465);
			x_street+=60;
			street.moveTo(x_street,465);
			
		}
		
		street.fillStyle = "Black";
		street.fillRect(0, 432, innerWidth, 500);	
		street.fillStyle = "SlateGray";	
		street.fillRect(0, 500, innerWidth, innerHeight);		
	street.stroke();
/****************************************************************************
								**HOUSES**
****************************************************************************/
	let x=10; let y=412;
	
	let casa_1 = document.getElementById("acanvas").getContext("2d");
		let casa_1_janelas = document.getElementById("acanvas").getContext("2d");
		let casa_1_teto = document.getElementById("acanvas").getContext("2d");
	let casa_2 = document.getElementById("acanvas").getContext("2d");
		let casa_2_janelas = document.getElementById("acanvas").getContext("2d");
		let casa_2_teto = document.getElementById("acanvas").getContext("2d");
	let casa_3 = document.getElementById("acanvas").getContext("2d");
		let casa_3_janelas = document.getElementById("acanvas").getContext("2d");

	//CASA 1 (ciclo diferente, pois é a unica que repete 5x no grafo de cena)
	for(let i=0 ; i<5 ; i++)
	{	
		//Contorno
		casa_1.beginPath();		
		casa_1.fillStyle = "salmon";
		casa_1.fillRect(x, 176, 50, 236);
		//Porta
		casa_1.fillStyle = "Sienna";
		casa_1.fillRect(x+5, 387, 15, 25);
		//Janelas
		let x_janelas = 0;
		while(x_janelas<45)
		{
			if(x_janelas == 25)
			{
				x_janelas+=5;
			}
			else
			{
				casa_1_janelas.beginPath();
				casa_1_janelas.fillRect( (x + x_janelas + 5) , 186, 2, 190);
				x_janelas+=5;
			}	
		}
		let teto_size = 60; let x_teto=x-5; let y_teto=176;
		//Teto
		for(let j=0 ; j<4 ; j++)
		{
			casa_1_teto.beginPath();
			casa_1_teto.fillStyle = "Sienna";
			casa_1_teto.fillRect(x_teto,y_teto-4,teto_size,4);
			x_teto+=5;
			teto_size-=10;
			y_teto-=4;
		}
		x_janelas = 0;
		x+=240;
	}
	
	x=60;
	let y_janelas = 310;

	//CASAS 2 e 3(ambas ocorrem 4x no grafo de cena)
	for(let i=0 ; i<4 ; i++)
	{
		//CASA 2
			//Contorno
			casa_2.beginPath();
			casa_2.fillStyle = "DarkSeaGreen";
			casa_2.fillRect(x, 272, 100, 140);
			//Porta
			casa_2.fillStyle = "LightGrey";
			casa_2.fillRect(x+30, 380, 34, 32);
			//Janelas
			while(y_janelas < 396)
			{
				casa_2_janelas.beginPath();
				casa_2_janelas.fillStyle = "white";
				casa_2_janelas.fillRect(x+5,y_janelas-30,40,5);
				casa_2_janelas.fillRect(x+55,y_janelas-30,40,5);

				y_janelas+=10;
			}
			//Teto
			let x_teto=x; let y_teto=268; let teto_size=100;
			for(let j=0 ; j<3 ; j++)
			{
				casa_2_teto.beginPath();
				casa_2_teto.fillStyle = "LightGrey";
				casa_2_teto.fillRect(x_teto,y_teto,teto_size,6);
				x_teto+=5;
				y_teto-=6;
				teto_size-=10;
			}

		//CASA 3
			//Contorno
			casa_3.beginPath();
			casa_3.fillStyle = "Moccasin";
			casa_3.fillRect(x+100, 232, 90, 180);
			//Porta
			casa_3.fillStyle = "Peru";
			casa_3.fillRect(x+130, 380, 34, 32);
			//Janelas  

			//x_janelas=x+100;
			y_janelas=372;
			while(y_janelas>262)
			{
				casa_3_janelas.beginPath();
				casa_3_janelas.fillStyle = "Peru";
				casa_3_janelas.fillRect((x+100)+10,y_janelas-30,10,10);
				casa_3_janelas.fillRect((x+100)+30,y_janelas-30,10,10);
				casa_3_janelas.fillRect((x+100)+50,y_janelas-30,10,10);
				casa_3_janelas.fillRect((x+100)+70,y_janelas-30,10,10);
				y_janelas-=20;
			}

		x+=240;
		y_janelas=310;
	}
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
			h.fillStyle = "seagreen";
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

function body(x,y) 
{
	let body = document.getElementById("acanvas").getContext("2d");

	this.x=x;
	this.y=y-10;
	let inicio = x;

	this.draw = function()
	{
		body.beginPath();
			body.fillStyle = "#0d4164";
			body.lineTo(this.x-8, this.y);
			body.lineTo(this.x-8, this.y);
			body.lineTo(this.x-8, this.y+38);
			body.lineTo(this.x+8, this.y+38);
			body.lineTo(this.x+8, this.y);
			body.lineTo(this.x, this.y);
		body.fill();	
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
			rLeg.fillStyle = " #efe5be";
			rLeg.moveTo(this.x,this.y+28);
			rLeg.lineTo(this.x-8, this.y+28);
			rLeg.lineTo(this.x-8, this.y+66);
			rLeg.lineTo(this.x, this.y+66);
			rLeg.lineTo(this.x, this.y+28);
		rLeg.fill();
		
		rShoe.beginPath();
			rShoe.strokeStyle = "#fcfcfc";
			rShoe.fillStyle = "#fcfcfc";
			rShoe.ellipse(this.x-2,this.y+68,8,3,Math.PI,0,2*Math.PI);
		rShoe.stroke();
		rShoe.fill();
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
			lLeg.fillStyle = " #efe5be";
			lLeg.moveTo(this.x,this.y+28);
			lLeg.lineTo(this.x+8,this.y+66);
			lLeg.lineTo(this.x+16,this.y+66);
			lLeg.lineTo(this.x+8,this.y+28);	
			lLeg.closePath();	
		lLeg.fill();
			

		lShoe.beginPath();
			lShoe.strokeStyle = "#fcfcfc";
			lShoe.fillStyle = "#fcfcfc";
			lShoe.ellipse(this.x+15,this.y+68,8,3,Math.PI,0,2*Math.PI);
		lShoe.fill();
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

function r_arm(x,y)
{
	let rArm = document.getElementById("acanvas").getContext("2d");

	this.x = x;
	this.y = y;
	let inicio = x;

	this.draw = function()
	{
		rArm.beginPath();
			rArm.fillStyle = "#0d4164";
			rArm.moveTo(this.x-8,this.y-10);
			rArm.lineTo(this.x-36,this.y+26);
			rArm.lineTo(this.x-28,this.y+30);
			rArm.lineTo(this.x-8,this.y);					
		rArm.fill();
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

function l_arm(x,y)
{
	let lArm = document.getElementById("acanvas").getContext("2d");

	this.x = x;
	this.y = y-10;
	let inicio = x;

	this.draw = function()
	{
		lArm.beginPath();
			lArm.fillStyle = "#0d4164";
			lArm.lineWidth = 1;
			lArm.moveTo(this.x+8,this.y);
			lArm.lineTo(this.x+26,this.y+40);
			lArm.lineTo(this.x+16,this.y+40);
			lArm.lineTo(this.x+8,this.y+16);			
		lArm.fill();	
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

function model(x,y)
{
	this.x=x;
	this.y=y;

	let cabeca = new head(-100,450);

	let corpo = new body(-100,450);

	let braco_d = new r_arm(-100,450);
	let braco_e = new l_arm(-100,450);

	let perna_d = new r_leg(-100,450);
	let perna_l = new l_leg(-100,450);	

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
					**FUNÇÃO PARA DEFINIR O OBJETO COMPANHEIRO & OBJETO EXTRA**
********************************************************************************************/
function object_draw(x,y)
{
	let car = document.getElementById("acanvas").getContext("2d");
	
	this.x=x;
	this.y=y;
	let inicio = x;

	this.draw = function()
	{
		//Objeto extra no objeto companheiro
			//Casa
			car.beginPath();
				car.fillStyle = "#66a2ab";
				car.fillRect(this.x+10,this.y-10,40,40);
				car.fillStyle = "#a4cbf7";
				car.fillRect(this.x+15,this.y-5,10,10);
				car.fillRect(this.x+35,this.y-5,10,10);
				car.fillStyle = "#604710";
				car.fillRect(this.x+25,this.y+15,10,15);
			car.fill();	
			//Teto
			car.beginPath();
				car.strokeStyle = "#604710";
				car.fillStyle = "#604710";
				car.lineTo(this.x+60, this.y-10);
				car.lineTo(this.x+40, this.y-25);
				car.lineTo(this.x+20, this.y-25);
				car.lineTo(this.x,this.y-10);
			car.fill();	
			car.stroke();
			//Bola
			car.beginPath();
				car.fillStyle = "orange";
				car.arc(this.x+70, this.y+20,15,0,2*Math.PI);
			car.fill();

		//Estrutura do objeto
		car.beginPath();
			car.fillStyle = "red";
			car.fillRect(this.x,this.y+20,110,20);
			car.fillRect(this.x, this.y+10, 10, 20);
			car.fillRect(this.x+100, this.y+10, 10, 20);
		//Rodas
		car.beginPath();
			car.fillStyle = "black";
			car.arc(this.x+20, this.y+50, 10, 0, 2 * Math.PI);
			car.arc(this.x+85, this.y+50, 10, 0, 2 * Math.PI);
		car.fill();
		//Cabo
		car.beginPath();
			car.moveTo(this.x+110,this.y+30);
			car.strokeStyle = "white"
			car.lineWidth = 3;
			car.lineTo(this.x+170,this.y+5);
			car.arc(this.x+168,this.y+5,5,(3*Math.PI)/4,(7*Math.PI)/4);
		car.stroke();
	}

	this.update = function() 
	{
		if(this.x + 450 >= innerWidth)
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
		//this.x+=2;
		//this.draw();
	}
}

function extra_object(x,y)
{
	let extra = document.getElementById("acanvas").getContext("2d");

	this.x = x;
	this.y = y;
	let inicio = x;

	this.draw = function()
	{
		//Caixa da carrinha
		extra.beginPath()
				extra.strokeStyle = "gray";
				extra.moveTo(this.x+145,this.y-35);
				extra.lineWidth = 3;
				extra.lineTo(this.x+110,this.y-60)
		extra.stroke();	

		//Estrutura da carrinha
		extra.beginPath();
			extra.fillStyle = "red";
			extra.moveTo(this.x,this.y);
			extra.lineTo(this.x+25,this.y);
			extra.arc(this.x+40,this.y,20,-(Math.PI),-(Math.PI * 2));
			extra.moveTo(this.x+55,this.y);
			extra.lineTo(this.x+105,this.y);
			extra.arc(this.x+120,this.y,20,-(Math.PI),-(Math.PI * 2));
			extra.moveTo(this.x+135,this.y);
			extra.lineTo(this.x+155,this.y);
			extra.lineTo(this.x+155,this.y-35);
			extra.lineTo(this.x+120,this.y-35);
			extra.lineTo(this.x+120,this.y-60);
			extra.lineTo(this.x+80,this.y-60);
			extra.lineTo(this.x+40,this.y-35);
			extra.lineTo(this.x,this.y-35);
			extra.lineTo(this.x,this.y);
		extra.fill();

		//Janela da carrinha
		extra.beginPath()
			extra.fillStyle = "white";
			extra.moveTo(this.x+40,this.y-35);
			extra.lineTo(this.x+80,this.y-35);
			extra.lineTo(this.x+80,this.y-60);
			extra.lineTo(this.x+40,this.y-35);
		extra.fill();
			
		//Pneus da carrinha
		extra.beginPath()
			extra.fillStyle = "gray";
			extra.arc(this.x+40,this.y,15,0,2*Math.PI);
			extra.arc(this.x+120,this.y,15,0,2*Math.PI);
		extra.fill();	
	}
	this.update = function()
	{
		if(this.x + 155 <= 0)
		{
			this.x = inicio;
			this.x-=3;
			this.draw();
		}
		else
		{
			this.x-=3;
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
	background_draw();
	
	//Definir o Modelo(pessoa)
	let modelo = new model(-100,450);

	//Definir Objeto Companheiro
	let objeto = new object_draw(-300,470);

	//Definir Objeto Extra
	let c_extra = new extra_object(1060,445);

	//let c_extra = new extra_object(500,445);
	
	//Função de Animação
	function animate()
    {
		requestAnimationFrame(animate);
			background_draw();	

			c_extra.update();
				
			objeto.update();

			modelo.update();			
	}
	
	//Animação
	animate();

	
	//c_extra.draw();
}