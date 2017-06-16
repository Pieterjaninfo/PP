use std::thread;

fn main() {
   let a = [6;100]; // initialise a to array with 100 times 6

   let sum1 = 0;
   let sum2 = 0;
   thread::spawn(|| {
      for i in 0 ..50 {
         sum1 = sum1 + a[i];
      }
   });

   thread::spawn(move || {
      for i in 50 .. 100 {
         sum2 = sum2 + a[i];
      }
   });
   println!("{}",sum1 + sum2);
}