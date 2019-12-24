package Model_Disk;

public class SystemDiskioBean {
    //SystemDiskioBean>diskiobean>iobean
    /*
  "system": {
    "diskio": {
      "iostat": {
        "await": 8.307692307692308,
        "service_time": 0.5192307692307693,
        "busy": 0.27098231087692887
      },
      "serial_number": "6c499d2dbe9f471daa8f",
      "name": "vda2",
      "io": {
        "time": 2480023
      }
    }
  },
     */
    private diskiobean diskio;
    public diskiobean getDiskio() {
        return diskio;
    }
    public void setDiskio(diskiobean diskio) {
        this.diskio = diskio;
    }

    public class diskiobean {
        private String name;
        private String serial_number;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getSerial_number() {
            return serial_number;
        }
        public void setSerial_number(String serial_number) {
            this.serial_number = serial_number;
        }

//        private readbean read;
//        private iwritebean write;
        private iostatbean iostat;
        private iobean io;

        public iostatbean getIostat() {
            return iostat;
        }
        public void setIostat(iostatbean iostat) {
            this.iostat = iostat;
        }

        public iobean getIo() {
            return io;
        }
        public void setIo(iobean io) {
            this.io = io;
        }

        public class iobean
        {
            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            private String time;

        }

        public class iostatbean
        {
            private String await;
            private String service_time;
            private String busy;

            public String getAwait() {
                return await;
            }

            public void setAwait(String await) {
                this.await = await;
            }

            public String getService_time() {
                return service_time;
            }

            public void setService_time(String service_time) {
                this.service_time = service_time;
            }

            public String getBusy() {
                return busy;
            }

            public void setBusy(String busy) {
                this.busy = busy;
            }
        }
    }


}
