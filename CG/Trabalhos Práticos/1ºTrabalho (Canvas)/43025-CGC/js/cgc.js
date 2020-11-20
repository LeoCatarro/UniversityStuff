/*******************************************************************************************
									**BACKGROUND**
********************************************************************************************/

/*
	-->Partes do Background
*/

function ocean()
{
	let mar = document.getElementById("acanvas").getContext("2d");
	let mar_gradient = mar.createLinearGradient(0,330,acanvas.width,480);
		mar_gradient.addColorStop(0.33,"#1ab2ff");
		mar_gradient.addColorStop(0.66,"#0088cc");

	mar.beginPath()
		mar.fillStyle=mar_gradient;
		mar.fillRect(0,330,acanvas.width,480);
	mar.closePath();
}

function wave(x,y)
{
	let wave = document.getElementById("acanvas").getContext("2d");
	let wave_gradient = wave.createLinearGradient(0,330,acanvas.width,480);
		wave_gradient.addColorStop(0.33,"#1ab2ff");
		wave_gradient.addColorStop(0.66,"#0088cc");
	
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
		if(this.x <= -50)
		{
			this.x = inicio;
			this.x-=2;
			this.draw();
		}
		else
		{
			this.x-=2;
			this.draw();
		}		
	}
}

function sky()
{
	let sky = document.getElementById("acanvas").getContext("2d");

	let sky_gradient = sky.createLinearGradient(0,0,acanvas.width, acanvas.height);
		sky_gradient.addColorStop(0.33,"#b3edff");
		sky_gradient.addColorStop(0.66,"#80e1ff");

	sky.beginPath();
		sky.fillStyle =sky_gradient;
		sky.fillRect(0, 0, acanvas.width, acanvas.height);
	sky.closePath();
}

function sun(x,y,r)
{
	let sun = document.getElementById("acanvas").getContext("2d");
	
	this.x=x;
	this.y=y;
	this.r=r;
	
	sun.beginPath();
		sun.fillStyle = "Gold";
		sun.arc(this.x, this.y, this.r, 0, 2 * Math.PI);
	sun.fill();
}

function clouds()
{
	let clouds = document.getElementById("acanvas").getContext("2d");

	let x_clouds = [50,270,680,950];
	let y_clouds = [80,50,120,75];
	let r_clouds = 15;

	for(let i=0 ; i< x_clouds.length ; i++)
	{
		clouds.beginPath();
			clouds.fillStyle = "white";
			clouds.arc(x_clouds[i], y_clouds[i], r_clouds, 0, 2*Math.PI);
		clouds.fill();

		clouds.beginPath();
			clouds.arc(x_clouds[i]+20, y_clouds[i]-10, r_clouds, 0, 2*Math.PI);
			clouds.fill();
			clouds.beginPath();
			clouds.arc(x_clouds[i]+20, y_clouds[i]+10, r_clouds, 0, 2*Math.PI);
			clouds.fill();

			clouds.arc(x_clouds[i]+40, y_clouds[i]-10, r_clouds, 0, 2*Math.PI);
			clouds.fill();
			clouds.beginPath();
			clouds.arc(x_clouds[i]+40, y_clouds[i]+10, r_clouds, 0, 2*Math.PI);
		clouds.fill();

		clouds.beginPath();
			clouds.arc(x_clouds[i]+60, y_clouds[i], r_clouds, 0, 2*Math.PI);
		clouds.fill();
	}
}

function birds()
{
	let bird = document.getElementById("acanvas").getContext("2d");

	let x_birds = [590,570,890,870,840,800]; let y_birds = [30,30,50,50,100,100]; let r_birds = [10,10,10,10,20,20];
	let color_bird = ["#666666","#333333","#262626"];
	let width_bird = [1,2,3];
		
	let i=0;
	while ( i<x_birds.length )
	{
		bird.beginPath();
			bird.strokeStyle = color_bird[i];
			bird.lineWidth = width_bird[i];
			bird.arc(x_birds[i], y_birds[i], r_birds[i], 0, Math.PI,true);
			bird.arc(x_birds[i+1], y_birds[i+1], r_birds[i+1], 0, Math.PI,true);
		bird.stroke();
		i+=2;
	}
}

function beach_umbrella()
{
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

}

function beach_towel()
{
	let toalha = document.getElementById("acanvas").getContext("2d");

	toalha.beginPath();
		toalha.fillStyle="#39ac6b";
		toalha.fillRect(250,470,40,80);
		toalha.fillStyle="#33995f";
		toalha.fillRect(350,470,40,80);
		toalha.fillStyle="#267347";
		toalha.fillRect(950,470,40,80);
}

function picnic_basket()
{
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

function beach_toys()
{
	let castle = document.getElementById("acanvas").getContext("2d");

	castle.beginPath();
		castle.fillStyle="#a6a6a6";
		castle.moveTo(55,500);
		castle.lineTo(100,500);

		castle.lineTo(100,460);
		castle.lineTo(95,460);
		castle.lineTo(95,465);
		castle.lineTo(90,465);
		castle.lineTo(90,460);
		castle.lineTo(85,460);
		castle.lineTo(85,475);

		castle.lineTo(70,475);

		castle.lineTo(70,460);
		castle.lineTo(65,460);
		castle.lineTo(65,465);
		castle.lineTo(60,465);
		castle.lineTo(60,460);
		castle.lineTo(55,460);
		castle.lineTo(55,500);
	castle.fill();
	
	castle.beginPath();
		castle.fillStyle="#996633";
		castle.moveTo(70,500);
		castle.lineTo(70,485);
		castle.quadraticCurveTo(77,480,85,485);
		castle.lineTo(85,500);
		castle.lineTo(70,500);
	castle.fill();	

	let shovel = document.getElementById("acanvas").getContext("2d");

	shovel.beginPath();
		shovel.lineWidth=3;
		shovel.strokeStyle="#996633";
		shovel.fillStyle="#996633";
		shovel.moveTo(120,500);
		shovel.lineTo(120,480);
		shovel.arcTo(115,480,115,470,5);
		shovel.lineTo(115,470);
		shovel.lineTo(125,470);
		shovel.arcTo(125,480,120,480,5);
	shovel.fill();
	shovel.stroke();	
}

function beach()
{
	let praia = document.getElementById("acanvas").getContext("2d");
	let praia_gradient = praia.createLinearGradient(0,450,acanvas.width,acanvas.height);
		praia_gradient.addColorStop(0.33, "#f4ebca");
		praia_gradient.addColorStop(0.99, "#ebd694");

	praia.beginPath()
		praia.fillStyle=praia_gradient;
		praia.fillRect(0,450,acanvas.width,acanvas.height);
	praia.closePath();				
	
	/*
	-->Chapéu de Sol && Toalhas && Outros
	*/

	beach_umbrella();

	beach_towel();
		
	picnic_basket();

	beach_toys();
}

/*
	-->Background Completo
*/

function background()
{
	sky();
	sun(50,50,35);
	clouds();
	birds();
	ocean();
	beach();
}

/*******************************************************************************************
								**MODELO DA FIGURA**
********************************************************************************************/

/*
	-->Partes do Modelo
*/

function hair(x,y)
{
    this.x=x;
    this.y=y;
    let inicio = x;

    this.draw = function()
    {
		let hair = document.getElementById("acanvas").getContext("2d");
		let hair_gradient = hair.createLinearGradient(0,330,acanvas.width,480);
			hair_gradient.addColorStop(0.33,"#804000");
			hair_gradient.addColorStop(0.66,"#663300");

        hair.beginPath();
			hair.fillStyle=hair_gradient;
			hair.moveTo(this.x,this.y);
			hair.bezierCurveTo(this.x+4,this.y-6,this.x+8,this.y+2,this.x+16,this.y-2);
			hair.quadraticCurveTo(this.x+30,this.y-2,this.x+26,this.y+10);
			hair.quadraticCurveTo(this.x+18,this.y+24,this.x+30,this.y+24);
			hair.quadraticCurveTo(this.x+32,this.y+22,this.x+30,this.y+18);
			hair.bezierCurveTo(this.x+26,this.y+18,this.x+46,this.y+8,this.x+30,this.y-6);
			hair.quadraticCurveTo(this.x+20,this.y-22,this.x,this.y-12);
			hair.quadraticCurveTo(this.x-8,this.y-16,this.x-2,this.y-20);
			hair.quadraticCurveTo(this.x-22,this.y-12,this.x,this.y);
        hair.fill();
    }

    this.update = function()
	{	
		this.x-=2;
		this.draw();
	
	}
}

function head(x,y)  
{
	let h = document.getElementById("acanvas").getContext("2d");

	this.x=x; //152
	this.y=y;  //88
	let inicio = x;

	this.draw = function()
	{
        //Pescoço
        h.beginPath();
            h.fillStyle="#ffc61a";
            h.moveTo(this.x,this.y);
            h.lineTo(this.x,this.y-18);
            h.lineTo(this.x-18,this.y-18);
            h.lineTo(this.x-18,this.y);
        h.fill();
		//Formato Cabeça
        h.beginPath();    
            h.moveTo(this.x-8,this.y-12);
            h.quadraticCurveTo(this.x-22,this.y-2,this.x-28,this.y-18);
            h.quadraticCurveTo(this.x-38,this.y-24,this.x-26,this.y-26);
            h.quadraticCurveTo(this.x-28,this.y-38,this.x-24,this.y-36);
            h.bezierCurveTo(this.x-20,this.y-42,this.x-16,this.y-34,this.x-8,this.y-38);
            h.quadraticCurveTo(this.x+6,this.y-38,this.x+2,this.y-26);
            h.quadraticCurveTo(this.x-2,this.y-12,this.x-8,this.y-12);
        h.fill();
		//Boca
        h.beginPath();
            h.moveTo(this.x-26,this.y-12);
            h.quadraticCurveTo(this.x-22,this.y-14,this.x-22,this.y-16);
		h.stroke();
		//Olho
		h.beginPath();
			h.moveTo(this.x-18,this.y-28)
			h.lineWidth=5;
			h.lineTo(this.x-16,this.y-28);
			h.lineWidth=1;
		h.stroke();
		//Sobrancelha
		h.beginPath();
			h.moveTo(this.x-18,this.y-32);
			h.quadraticCurveTo(this.x-16,this.y-34,this.x-14, this.y-32);
		h.stroke();		
	}

	this.update = function()
	{

		this.x-=2;
		this.draw();
	}
}

function body(x,y) 
{
	let body = document.getElementById("acanvas").getContext("2d");
	let calcoes = document.getElementById("acanvas").getContext("2d");		


	this.x=x;  
	this.y=y;
	let inicio = x;

	this.draw = function()
	{
        //-->Blusa
        body.beginPath();
			body.fillStyle="#ff8000";
			body.moveTo(this.x ,this.y);
			body.lineTo(this.x+16,this.y-2);
			body.quadraticCurveTo(this.x+32,this.y+2,this.x+40,this.y-40);
			body.moveTo(this.x+40,this.y-34);
			body.quadraticCurveTo(this.x+40,this.y-32,this.x+52,this.y-42);
			body.quadraticCurveTo(this.x+40,this.y-32,this.x+52,this.y-42);
			body.quadraticCurveTo(this.x+32,this.y-48,this.x+24,this.y-46);
			body.quadraticCurveTo(this.x+17,this.y-34,this.x+4,this.y-46);
			body.quadraticCurveTo(this.x-12,this.y-44,this.x-22,this.y-42);
			body.quadraticCurveTo(this.x-20,this.y-40,this.x-16,this.y-30);
			body.lineTo(this.x-4,this.y-32);
			body.lineTo(this.x,this.y);
        body.fill();
            
        //--> Calções
        calcoes.beginPath();
        calcoes.fillStyle="#666666";
			calcoes.moveTo(this.x,this.y);
			calcoes.quadraticCurveTo(this.x-4,this.y+10,this.x+4,this.y+30);
			calcoes.quadraticCurveTo(this.x+10,this.y+32,this.x+20,this.y+28);
			calcoes.quadraticCurveTo(this.x+16,this.y+20,this.x+18,this.y+12);
			calcoes.quadraticCurveTo(this.x+24,this.y+20,this.x+30,this.y+32);
			calcoes.lineTo(this.x+44,this.y+20);
			calcoes.quadraticCurveTo(this.x+42,this.y+20,this.x+26,this.y-6);
		calcoes.fill();
	}

	this.update = function()
	{
	
		this.x-=2;
		this.draw();
	}

}

function r_leg(x,y)
{
	let rLeg = document.getElementById("acanvas").getContext("2d");

	this.x = x;
	this.y = y;  
	let inicio = x;

	this.draw = function()
	{
        rLeg.beginPath();
        rLeg.fillStyle="#ffc61a";
        rLeg.moveTo(this.x,this.y);
        rLeg.lineTo(this.x-6,this.y+22);
        rLeg.quadraticCurveTo(this.x-10,this.y+28,this.x,this.y+34);
        rLeg.quadraticCurveTo(this.x+12,this.y+36,this.x+2,this.y+28);
        rLeg.bezierCurveTo(this.x-2,this.y+14,this.x+18,this.y+2,this.x+14,this.y-2);
    rLeg.fill();
	}

	this.update = function()
	{	
		this.x-=2;
		this.draw();
	}
	
}

function l_leg(x,y)
{
	let lLeg = document.getElementById("acanvas").getContext("2d");

	this.x = x;
	this.y = y;
	let inicio = x;

	this.draw = function()
	{
		lLeg.beginPath();
        	lLeg.fillStyle="#ffc61a";
            lLeg.moveTo(this.x,this.y);
            lLeg.lineTo(this.x+6,this.y+6);
            lLeg.lineTo(this.x+6,this.y+6);
            lLeg.lineTo(this.x-2,this.y+30);
            lLeg.quadraticCurveTo(this.x-6,this.y+32,this.x+2,this.y+40);
            lLeg.quadraticCurveTo(this.x+10,this.y+42,this.x+8,this.y+34);
            lLeg.bezierCurveTo(this.x-2,this.y+30,this.x+22,this.y+4,this.x+14,this.y-6);
        lLeg.fill();
	}

	this.update = function()
	{	
		this.x-=2;
		this.draw();
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
			rArm.fillStyle="#ffc61a";
			rArm.moveTo(this.x,this.y);
			rArm.lineTo(this.x-12,this.y+4);
			rArm.lineTo(this.x-16,this.y+30);
			rArm.quadraticCurveTo(this.x-14,this.y+32,this.x-16,this.y+42);
			rArm.quadraticCurveTo(this.x-18,this.y+44,this.x-20,this.y+34);
			rArm.quadraticCurveTo(this.x-26,this.y+40,this.x-24,this.y+44);
			rArm.quadraticCurveTo(this.x-40,this.y+48,this.x-30,this.y+28);
			rArm.quadraticCurveTo(this.x-24,this.y+18,this.x-22,this.y+8);
			rArm.quadraticCurveTo(this.x-22,this.y-4,this.x-4,this.y-12);
        rArm.fill();
	}

	this.update = function()
	{
		
		this.x-=2;
		this.draw();

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
			lArm.fillStyle="#ffc61a";
			lArm.moveTo(this.x,this.y);
			lArm.quadraticCurveTo(this.x+12,this.y+10,this.x+52,this.y+21);
			lArm.quadraticCurveTo(this.x+67,this.y+16,this.x+32,this.y+6);
			lArm.quadraticCurveTo(this.x+32,this.y+6,this.x+12,this.y-8);
        lArm.fill();	
	}

	this.update = function()
	{
		this.x-=2;
		this.draw();
	}
	
}

/*
	-->Modelo Completo
*/

function model(x,y)
{
	this.x=x; 
    this.y=y; 
    let inicio = x;

    let cabeca = new head(this.x+24,this.y-38);
    
    let braco_l = new l_arm(this.x+40,this.y-24);
    
    let braco_r = new r_arm(this.x-16,this.y-30);
    
    let perna_l = new l_leg(this.x+30,this.y+28);
    
    let perna_r = new r_leg(this.x+5,this.y+30);
    
    let corpo = new body(this.x,this.y);
  
    let cabelo = new hair(this.x,this.y-72);
  

    this.draw = function()
    {
        cabeca.draw();
        braco_l.draw();
        braco_r.draw();
        perna_l.draw(),
        perna_r.draw();
        corpo.draw();
        cabelo.draw();

    }

    this.update = function()
    {
		if(braco_l.x <= -100)
		{
			cabeca.x = inicio + 24;
			braco_l.x = inicio + 40;
			braco_r.x = inicio - 16;
			perna_l.x = inicio + 30;
			perna_r.x = inicio + 5;
			corpo.x = inicio;
			cabelo.x = inicio;
		}
        cabeca.update();
        braco_l.update();
        braco_r.update();
        perna_l.update();
        perna_r.update();
        corpo.update();
        cabelo.update();
    }
}

/*******************************************************************************************
							**OBJETO COMPANHEIRO**
********************************************************************************************/

function object(x,y)
{
	this.x=x;
	this.y=y;
	let inicio = x;

	let prancha = document.getElementById("acanvas").getContext("2d");
	
	let prancha_gradient = prancha.createLinearGradient(this.x+25,this.y+5, this.x+25,this.y-35);
		prancha_gradient.addColorStop(0.20, "yellow");
		prancha_gradient.addColorStop(0.20, "seagreen");
		prancha_gradient.addColorStop(0.30, "seagreen");
		prancha_gradient.addColorStop(0.30, "white");
		prancha_gradient.addColorStop(0.50, "white");
		prancha_gradient.addColorStop(0.50, "seagreen");
		prancha_gradient.addColorStop(0.60, "seagreen");
		prancha_gradient.addColorStop(0.60, "yellow");
	
	this.draw = function()
	{
       prancha.beginPath();
			prancha.fillStyle=prancha_gradient;
			prancha.moveTo(this.x,this.y);
			prancha.quadraticCurveTo(this.x+25,this.y+5,this.x+100,this.y-5);
			prancha.quadraticCurveTo(this.x+110,this.y-15,this.x+100,this.y-25);
			prancha.quadraticCurveTo(this.x+25,this.y-35,this.x-20,this.y-15);
			prancha.quadraticCurveTo(this.x-40,this.y-5,this.x,this.y);
        prancha.fill();	
	}

	this.update = function()
	{
		if(this.x <= -180)
		{
			this.x = inicio;
			this.x-=2;
			this.draw();
		}
		else
		{	
			this.x-=2;
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
    let fundo = background();
	
    //Definir Objeto Companheiro
    let objeto = new object(1020,337);
    objeto.draw()
	
	//Definir o Modelo(pessoa)
    let modelo = new model(1060,260);
	modelo.draw();
	
	//Definir Onda
	let onda = new wave(1150,340);
	onda.draw();	
	

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