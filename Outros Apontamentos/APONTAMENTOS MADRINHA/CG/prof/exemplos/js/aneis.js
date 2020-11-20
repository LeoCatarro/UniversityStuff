RingsApp = function() {
	Sim.App.call(this);
}

RingsApp.prototype = new Sim.App();

RingsApp.prototype.init = function(param)
{
	Sim.App.prototype.init.call(this, param);
    var rings = new Rings();
    rings.init();
    this.addObject(rings);
}

Rings = function() {
	Sim.Object.call(this);
}

Rings.prototype = new Sim.Object();
Rings.prototype.init = function() {
    var group = new THREE.Object3D();
    this.setObject3D(group);
    this.ringsGroup = group;
    this.createRings();
}
//
//
//
Rings.prototype.createRings = function() {      
    //
    //
    //
    var geometry = new RingsGeometry(1.1, 1.5, 64);
    //
    //
    //
    var ringsmap = "../media/saturn_ring.png";     
    var texture = THREE.ImageUtils.loadTexture(ringsmap);
    var material = new THREE.MeshLambertMaterial( {
        map: texture,
        transparent:false,
        ambient:0xffffff } );
    //
    //
    //
    material.side = THREE.DoubleSide;
    //
    //
    //
    var mesh = new THREE.Mesh( geometry, material );
    mesh.rotation.x = 2.0* Math.PI/2 * 0.0;
    mesh.rotation.y = 2.0* Math.PI/2 * 0.0;
    mesh.rotation.z = 2.0* Math.PI/2 * 0.0;
    mesh.position.x = 0.0;
    mesh.position.y = 0.0;
    mesh.position.z = -1.0;
    //
    //
    //
    this.ringsGroup.add(mesh);
    //
    //
    //
    this.ringsMesh = mesh;
}

//
//  RingsGeometry é uma subclasse de THREE.Geometry
//
RingsGeometry = function ( innerRadius, outerRadius, nSegments ) {   
    THREE.Geometry.call( this );
    //
    //  "Limpar" os parâmetros
    //
    var outerRadius = outerRadius || 1,
    innerRadius = innerRadius || .5,
    gridY = nSegments || 10;
    //
    //  Declarar algumas variáveis
    //
    var i, twopi = 2 * Math.PI,
    uvs = [],
    iVer = Math.max( 2, gridY );
    //
    //  Para cada sector
    //  
    for ( i = 0; i < ( iVer + 1 ) ; i++ ) {
        //
        //  fRad1 ~ alpha
        //  fRad2 ~ alpha + delta
        //
        var alpha = twopi * i / iVer;
        var alpha_delta = twopi * (i + 1) / iVer;
        //
        //  Calcular as coordenadas dos vértices
        //
        var v0x = innerRadius * Math.cos( alpha );
        var v0y = innerRadius * Math.sin( alpha );
        //
        var v1x = outerRadius * Math.cos( alpha );
        var v1y = outerRadius * Math.sin( alpha );
        //
        var v2x = outerRadius * Math.cos( alpha_delta );
        var v2y = outerRadius * Math.sin( alpha_delta );
        //
        var v3x = innerRadius * Math.cos( alpha_delta );
        var v3y = innerRadius * Math.sin( alpha_delta );
        //
        //  Fazer os vértices
        //
        var v0 = new THREE.Vector3( v0x, v0y, 0 );
        //
        var v1 = new THREE.Vector3( v1x, v1y, 0 );
        //
        var v2 = new THREE.Vector3( v2x, v2y, 0 );
        //
        var v3 = new THREE.Vector3( v3x, v3y, 0 );
        //
        //  Juntar ao conjunto de vértices desta geometria
        //
        this.vertices.push( v0 );
        //
        this.vertices.push( v1 );
        //
        this.vertices.push( v2 );
        //
        this.vertices.push( v3 );   
    }
    //
    //  Fazer os triângulos.
    //
    //  N.B. Cada triângulo define uma superfície.
    //  Para que esta geometria se comporte "bem" quando iluminada
    //  em cada vértice do triângulo tem de estar definida a normal à superfície.
    //
    //  Como toda o geometria está no plano XY, a normal é constante: (0,0,1)
    //
    var n = new THREE.Vector3( 0, 0, 1 );
    //
    //  Para cada sector...
    //
    for ( i = 0; i < iVer ; i++ ) {
        var k = 4 * i; // índice do vértice no sector
        //
        //  Definir os triângulos
        //
        this.faces.push(new THREE.Face3(
            k + 0,
            k + 1,
            k + 2,
            [ n.clone(), n.clone(), n.clone() ] ));
        this.faces.push(new THREE.Face3(
            k + 0,
            k + 2,
            k + 3,
            [ n.clone(), n.clone(), n.clone() ] ));
        //
        //  Definir o mapa vértice --> UV
        //
        if (true) {
            this.faceVertexUvs[ 0 ].push([
                new THREE.Vector2(0,0),
                new THREE.Vector2(1,0),
                new THREE.Vector2(1,1) ] );        
            this.faceVertexUvs[ 0 ].push([
                new THREE.Vector2(0,0),
                new THREE.Vector2(1,1),
                new THREE.Vector2(0,1) ] );
        } else {
            //
            //  Exemplo com mapas errados, para efeito de ilustração
            //
            this.faceVertexUvs[ 0 ].push([
                new THREE.Vector2(Math.random(),Math.random()),
                new THREE.Vector2(Math.random(),Math.random()),
                new THREE.Vector2(Math.random(),Math.random()) ] );        
            this.faceVertexUvs[ 0 ].push([
                new THREE.Vector2(Math.random(),Math.random()),
                new THREE.Vector2(Math.random(),Math.random()),
                new THREE.Vector2(Math.random(),Math.random()) ] );
        }
    }   
    //
    //  "Arrumar" as normais desta geometria
    //
    this.computeFaceNormals();
    //
    //  Definir o "envelope" esférico da geometria
    //
    this.boundingSphere = new THREE.Sphere( new THREE.Vector3(), outerRadius );
};
RingsGeometry.prototype = Object.create( THREE.Geometry.prototype ) ;
RingsGeometry.prototype.constructor = RingsGeometry;

