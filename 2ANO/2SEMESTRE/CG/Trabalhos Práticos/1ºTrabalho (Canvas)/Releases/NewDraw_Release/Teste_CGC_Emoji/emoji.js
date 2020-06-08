

function main()
{
    let p = document.getElementById("acanvas").getContext("2d");

        p.beginPath();
            p.strokStyle="black";
            p.moveTo(0,0);
            p.lineTo(acanvas.width,0);
            p.lineTo(acanvas.width,acanvas.height);
            p.lineTo(0,acanvas.height);
            p.lineTo(0,0);
        p.stroke();


        /*
            Prancha
        */
            p.beginPath();
                p.fillStyle="blue";
                p.moveTo(100,205);
                p.quadraticCurveTo(125,210,200,200);
                p.quadraticCurveTo(210,190,200,180);
                p.quadraticCurveTo(125,170,80,190);
                p.quadraticCurveTo(60,200,100,205);
            p.stroke();
            p.fill();


        /*
            Cabeça 
        */
            p.beginPath();
                p.fillStyle="pink";
                p.moveTo(152,88);
                p.lineTo(152,70);
                p.lineTo(134,70);
                p.lineTo(134,88);
            p.fill();

            p.beginPath();    
                p.moveTo(144,76);
                p.quadraticCurveTo(130,86,124,70);
                p.quadraticCurveTo(114,64,126,62);
                //iniciar cabelo em (124,50)
                p.quadraticCurveTo(124,50,128,52);
                p.bezierCurveTo(132,46,136,54,144,50);
                p.quadraticCurveTo(158,50,154,62);
                p.quadraticCurveTo(150,76,144,76);
            p.stroke();
            p.fill();

            p.beginPath();
                p.arcTo(134,60,136,60,6);
                p.moveTo(126,76);
                p.quadraticCurveTo(130,74,130,72);
            p.stroke();    

        /*
            Cabelo
        */    
            p.beginPath();
            p.fillStyle="brown";
                p.moveTo(128,52);
                p.bezierCurveTo(132,46,136,54,144,50);
                p.quadraticCurveTo(158,50,154,62);
                p.quadraticCurveTo(146,76,158,76);
                p.quadraticCurveTo(160,74,158,70);
                p.bezierCurveTo(154,70,174,60,158,46);
                p.quadraticCurveTo(148,30,128,40);
                p.quadraticCurveTo(120,36,126,32);
                p.quadraticCurveTo(106,40,128,52);
            p.stroke();
            p.fill();


        /*
            Tronco
        */
            //-->Blusa
            p.beginPath();
                p.fillStyle="orange";
                p.moveTo(128,128);
                p.lineTo(144,126);
                p.quadraticCurveTo(160,130,168,88);
                p.moveTo(168,94);
                p.quadraticCurveTo(168,96,180,86);
                p.quadraticCurveTo(168,96,180,86);
                p.quadraticCurveTo(180,80,152,82);
                p.quadraticCurveTo(145,94,132,82);
                p.quadraticCurveTo(116,84,106,86);
                p.quadraticCurveTo(108,88,112,98);
                p.lineTo(124,96);
                p.lineTo(128,128);
            p.stroke();
            p.fill();
            
            //--> Calções
            p.beginPath();
            p.fillStyle="gray";
                p.moveTo(128,128);
                p.quadraticCurveTo(124,138,132,158);
                p.quadraticCurveTo(138,160,148,156);
                p.quadraticCurveTo(144,148,146,140);
                p.quadraticCurveTo(152,148,158,150);
                p.lineTo(172,148);
                p.quadraticCurveTo(170,148,154,122);
            p.stroke();
            p.fill();


        /*
            Pernas
        */
            //-->Perna Direita
            p.beginPath();
                p.fillStyle="pink";
                p.moveTo(132,158);
                p.lineTo(126,180);
                p.quadraticCurveTo(122,186,132,192);
                p.quadraticCurveTo(144,194,134,186);
                //p.quadraticCurveTo(142,198,132,190);
                p.bezierCurveTo(130,172,150,160,146,156);
            p.stroke();
            p.fill();

            //-->Perna Esquerda
            p.beginPath();
                p.fillStyle="pink";
                p.moveTo(158,150);
                p.lineTo(164,156);
                p.lineTo(164,156);
                p.lineTo(156,180);
                p.quadraticCurveTo(152,182,160,190);
                p.quadraticCurveTo(168,192,166,184);
                p.bezierCurveTo(156,180,180,154,172,148);
            p.stroke();
            p.fill();


        /*
            Braços 
        */    
            //-->Braço Direito
            p.beginPath();
                p.fillStyle="pink";
                p.moveTo(112,98);
                p.lineTo(100,102);
                p.lineTo(96,128);
                p.quadraticCurveTo(98,130,96,140);
                p.quadraticCurveTo(94,142,92,132);
                p.quadraticCurveTo(86,138,88,142);
                p.quadraticCurveTo(72,146,82,126);
                p.quadraticCurveTo(88,116,90,106);
                p.quadraticCurveTo(90,94,108,86);
            p.stroke();
            p.fill();

            //-->Braço Esquerdo
            p.beginPath();
                p.fillStyle="pink";
                p.moveTo(168,94);
                p.quadraticCurveTo(180,104,220,115);
                p.quadraticCurveTo(235,110,200,100);
                p.quadraticCurveTo(200,100,180,86);
            p.stroke();
            p.fill();
}