package Model_Mem;



public class Actualbean
    {
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
            private String pct;

            public String getPct() {
                return pct;
            }

            public void setPct(String pct) {
                this.pct = pct;
            }
        }
    }


