function grad_1(c){
    var g = c.createRadialGradient(50, 50, 1, 50, 50,50);
    g.addColorStop(0.0, "red");
    g.addColorStop(1.0, "blue");
    return g;
}

function main(){
    var c = document.getElementById("acanvas").getContext("2d");
    c.fillStyle = grad_1(c);
    c.fillRect(25,25,50,50);
}
