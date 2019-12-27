package Model_Mem;


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
        private Actualbean actual;
        private usedbean used;

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

        public Actualbean getActual() {
            return actual;
        }

        public void setActual(Actualbean actual) {
            this.actual = actual;
        }

        public usedbean getUsed() {
            return used;
        }

        public void setUsed(usedbean used) {
            this.used = used;
        }
/*        public class actualbean
        {
            *//*
      "actual": {
      "used":{}
    }
         *//*
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
                *//*           "free": 3005034496,
                             "used": {
                                 "bytes": 15317372928,
                                  "pct": 0.836  //取真实内存的百分比
                              }*//*
                private String pct;
                public String getPct() {
                    return pct;
                }
                public void setPct(String pct) {
                    this.pct = pct;
                }

            }
        }*/

        public class usedbean
        {
            private double pct;

            public double getPct() {
                return pct;
            }

            public void setPct(double pct) {
                this.pct = pct;
            }


        }
    }

}
