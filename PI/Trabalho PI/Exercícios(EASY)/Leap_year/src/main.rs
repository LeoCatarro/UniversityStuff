fn main() {
    let year = 1907;  //COM ANO SENDO DADO E NÃO PEDIDO AO UTILIZADOR!
    if year % 4 != 0 {
        println!("That's not a LeapYear");
    }
    else if year % 100 != 0  {
        println!("That's not a LeapYear");
    }
    else if year % 400 !=0 {
        println!("Thats not a LeapYear");
    }
    else {
        println!("That's a LeapYear");
    }
}
