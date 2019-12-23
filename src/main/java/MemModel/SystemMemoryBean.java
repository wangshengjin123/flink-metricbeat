package MemModel;

public class SystemMemoryBean {

    /*
  "system": {
    "memory": {
      "actual": {
        "free": 3005034496,
        "used": {
          "bytes": 15317372928,
          "pct": 0.836  //取真实内存的百分比
        }
      },
      "total": 18322407424,
      "used": {
        "bytes": 18120048640,
        "pct": 0.989
      },
      "free": 202358784
    }
  }
     */
    private memeorybean memory;
    public memeorybean getMemory() {
        return memory;
    }
    public void setMemory(memeorybean memory) {
        this.memory = memory;
    }

    public class memeorybean {
        /*
         "memory": {
         }
         */
        private String total;
        private String free;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getFree() {
            return free;
        }

        public void setFree(String free) {
            this.free = free;
        }

        private usedbean used1;
        private actualbean actual;
        public usedbean getUsed1() {return used1; }
        public void setUsed1(usedbean used1) {this.used1 = used1; }

        public actualbean getActual() {
            return actual;
        }

        public void setActual(actualbean actual) {
            this.actual = actual;
        }

        public class actualbean
        {
            /*
      "actual": {
      "used":{}
    }
         */
            private String free;

            public String getFree() {
                return free;
            }

            public void setFree(String free) {
                this.free = free;
            }

            private usedbean used;

            public usedbean getUsed() {
                return used;
            }

            public void setUsed(usedbean used) {
                this.used = used;
            }

            public class usedbean {
/*           "free": 3005034496,
             "used": {
                 "bytes": 15317372928,
                  "pct": 0.836  //取真实内存的百分比
              }*/
                private String pct;
                public String getPct() {
                    return pct;
                }
                public void setPct(String pct) {
                    this.pct = pct;
                }

            }
        }
        public class usedbean
        {
            private String pct;

            public String getPct() {
                return pct;
            }

            public void setPct(String pct) {
                this.pct = pct;
            }

            @Override
            public String toString() {
                return "usedbean{" +
                        "pct='" + pct + '\'' +
                        '}';
            }
        }
    }

}
