package Model_Cpu;

public class SystemCpuBean {
    private Cpubean cpu;

    public Cpubean getCpu() {
        return cpu;
    }

    public void setCpu(Cpubean cpu) {
        this.cpu = cpu;
    }


    public class Cpubean {
        private int cores;
        private softirqbean softirq;
        private stealbean steal;
        private systembean system;
        private idlebean idle;
        private irqbean irq;
        private totalbean total;
        private nicebean nice;
        private userbean user;
        private iowaitbean iowait;

        public int getCores() {
            return cores;
        }

        public void setCores(int cores) {
            this.cores = cores;
        }

        public softirqbean getSoftirq() {
            return softirq;
        }

        public void setSoftirq(softirqbean softirq) {
            this.softirq = softirq;
        }

        public stealbean getSteal() {
            return steal;
        }

        public void setSteal(stealbean steal) {
            this.steal = steal;
        }

        public systembean getSystem() {
            return system;
        }

        public void setSystem(systembean system) {
            this.system = system;
        }

        public idlebean getIdle() {
            return idle;
        }

        public void setIdle(idlebean idle) {
            this.idle = idle;
        }

        public irqbean getIrq() {
            return irq;
        }

        public void setIrq(irqbean irq) {
            this.irq = irq;
        }

        public totalbean getTotal() {
            return total;
        }

        public void setTotal(totalbean total) {
            this.total = total;
        }

        public nicebean getNice() {
            return nice;
        }

        public void setNice(nicebean nice) {
            this.nice = nice;
        }

        public userbean getUser() {
            return user;
        }

        public void setUser(userbean user) {
            this.user = user;
        }

        public iowaitbean getIowait() {
            return iowait;
        }

        public void setIowait(iowaitbean iowait) {
            this.iowait = iowait;
        }
        public class softirqbean {
            private double pct;

            public double getPct() {
                return pct;
            }

            public void setPct(double pct) {
                this.pct = pct;
            }
        }
        public class stealbean {
            private double pct;

            public double getPct() {
                return pct;
            }

            public void setPct(double pct) {
                this.pct = pct;
            }
        }
        public class systembean {
            private double pct;

            public double getPct() {
                return pct;
            }

            public void setPct(double pct) {
                this.pct = pct;
            }
        }
        public class idlebean {
            private double pct;

            public double getPct() {
                return pct;
            }

            public void setPct(double pct) {
                this.pct = pct;
            }
        }
        public class irqbean {
            private double pct;

            public double getPct() {
                return pct;
            }

            public void setPct(double pct) {
                this.pct = pct;
            }
        }
        public class totalbean {
            private double pct;

            public double getPct() {
                return pct;
            }

            public void setPct(double pct) {
                this.pct = pct;
            }
        }
        public class nicebean {
            private double pct;

            public double getPct() {
                return pct;
            }

            public void setPct(double pct) {
                this.pct = pct;
            }
        }
        public class userbean {
            private double pct;

            public double getPct() {
                return pct;
            }

            public void setPct(double pct) {
                this.pct = pct;
            }
        }
        public class iowaitbean {
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
