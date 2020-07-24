function range(a,b){
	
	let arr = [];
	
	for(let i=a ; i<=b ; i++)
	{
		if(i % 2 === 0)
			arr.push(i);
	}
	return arr;
}

function main(){

	let consola = document.getElementById("xpto");
	let resposta = range(0,100);
	console.log(resposta);
	consola.innerHTML = resposta;
}
