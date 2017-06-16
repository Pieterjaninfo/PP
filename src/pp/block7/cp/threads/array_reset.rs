use std::thread;



fn main() {
   let mut a = [6;100]; // initialise a to array with 100 times 6

   thread::spawn(move || {
      for i in 0 ..50 {
         a[i] = 0;
      }
   });

   thread::spawn(move || {
      for i in 50 .. 100 {
         a[i] = 0;
      }
   });


}