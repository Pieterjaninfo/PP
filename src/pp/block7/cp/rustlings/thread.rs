// Make this compile! The idea is the thread
// spawned on line 17 is completing jobs while the main thread is
// monitoring progress until 10 jobs are completed. If you see 6 lines
// of "waiting..." and the program ends without timing out the playground,
// you've got it :)

use std::sync::{Arc,Mutex};
use std::thread;
use std::time::Duration;

struct JobStatus {
    jobs_completed: u32,
}

fn main() {
    let status = Arc::new(Mutex::new(JobStatus { jobs_completed: 0 }));
    let status_shared = status.clone(); //clone cuz of while loop
    thread::spawn(move || {
        for _ in 0..10 {
            let mut status_shared = status.lock().unwrap();
            thread::sleep(Duration::from_millis(250));
            status_shared.jobs_completed += 1;
        }
    });
    while status_shared.lock().unwrap().jobs_completed < 10 {
        println!("waiting... ");
        thread::sleep(Duration::from_millis(500));
    }
}



