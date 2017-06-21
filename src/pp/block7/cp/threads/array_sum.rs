use std::thread;

fn main() {
   let a = [6;100]; // initialise a to array with 100 times 6

   let mut sum1 = 0;
   let mut sum2 = 0;

   let t1 = thread::spawn(move || {
      for i in 0 ..50 {
         sum1 = sum1 + a[i];
      }
      sum1
   });

   let t2 = thread::spawn(move || {
      for i in 50 .. 100 {
         sum2 = sum2 + a[i];
      }
      sum2
   });
   println!("{}",t1.join().unwrap() + t2.join().unwrap());
}
