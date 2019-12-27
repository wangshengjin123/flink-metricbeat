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

        private usedbeanxxx used;

        public usedbeanxxx getUsed() {
            return used;
        }

        public void setUsed(usedbeanxxx used) {
            this.used = used;
        }

        public class usedbeanxxx {
            private String pct;

            public String getPct() {
                return pct;
            }

            public void setPct(String pct) {
                this.pct = pct;
            }
        }
    }


