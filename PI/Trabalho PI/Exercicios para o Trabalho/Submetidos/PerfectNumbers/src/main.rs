fn main ( ) {
    fn factor_sum(n: i32) -> i32 {
        let mut v = Vec::new(); //criar um novo array vazio
        for  x in 1..n-1 {      //testa valores desde 1 a n-1
            if n%x == 0 {   //se x é divisivel por n
                v.push(x);      //adiciona x ao array
            }
        }
        let mut sum = v.iter().sum(); //itera ao longo do array e soma-o
    }

    fn perfect_nums(n: i32) {
        for x in 2..n {       //testa numeros de 1-n
            if factor_sum(x) == x {//chama a factor_sum em cada valor de x, se o valor de retorno é = x
                println!("{} is a perfect number.", x); //apresenta o valor de x
            }
        }
    }
    //VAI PROCURAR OS NUMEROS PFERFEITO ATÉ n
    perfect_nums(7);
}