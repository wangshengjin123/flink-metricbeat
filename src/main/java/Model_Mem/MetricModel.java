package Model_Mem;



import java.util.List;

public class MetricModel {


    /**
     * system : {"memory":{"actual":{"free":6912364544,"used":{"bytes":1477447680,"pct":0.1761}},"swap":{"total":0,"used":{"pct":0,"bytes":0},"free":0},"hugepages":{"used":{"bytes":0,"pct":0},"free":0,"reserved":0,"surplus":0,"default_size":2097152,"total":0},"total":8389812224,"used":{"bytes":1539088384,"pct":0.1834},"free":6850723840}}
     * beat : {"name":"server14sudo","hostname":"server14sudo","version":"6.5.4"}
     * host : {"name":"server14sudo","architecture":"x86_64","os":{"platform":"centos","version":"6.9 (Final)","family":"redhat","codename":"Final"},"containerized":true,"ip":["192.168.23.2"],"mac":["00:0c:29:7f:09:ef"]}
     * metricset : {"name":"memory","module":"system","rtt":923}
     */

    private SystemBean system;
    private BeatBean beat;
    private HostBean host;
    private MetricsetBean metricset;

    public SystemBean getSystem() {
        return system;
    }

    public void setSystem(SystemBean system) {
        this.system = system;
    }

    public BeatBean getBeat() {
        return beat;
    }

    public void setBeat(BeatBean beat) {
        this.beat = beat;
    }

    public HostBean getHost() {
        return host;
    }

    public void setHost(HostBean host) {
        this.host = host;
    }

    public MetricsetBean getMetricset() {
        return metricset;
    }

    public void setMetricset(MetricsetBean metricset) {
        this.metricset = metricset;
    }

    public static class SystemBean {
        /**
         * memory : {"actual":{"free":6912364544,"used":{"bytes":1477447680,"pct":0.1761}},"swap":{"total":0,"used":{"pct":0,"bytes":0},"free":0},"hugepages":{"used":{"bytes":0,"pct":0},"free":0,"reserved":0,"surplus":0,"default_size":2097152,"total":0},"total":8389812224,"used":{"bytes":1539088384,"pct":0.1834},"free":6850723840}
         */

        private MemoryBean memory;

        public MemoryBean getMemory() {
            return memory;
        }

        public void setMemory(MemoryBean memory) {
            this.memory = memory;
        }

        public static class MemoryBean {
            /**
             * actual : {"free":6912364544,"used":{"bytes":1477447680,"pct":0.1761}}
             * swap : {"total":0,"used":{"pct":0,"bytes":0},"free":0}
             * hugepages : {"used":{"bytes":0,"pct":0},"free":0,"reserved":0,"surplus":0,"default_size":2097152,"total":0}
             * total : 8389812224
             * used : {"bytes":1539088384,"pct":0.1834}
             * free : 6850723840
             */

            private ActualBean actual;
            private SwapBean swap;
            private HugepagesBean hugepages;
            private long total;
            private UsedBeanXXX used;
            private long free;

            public ActualBean getActual() {
                return actual;
            }

            public void setActual(ActualBean actual) {
                this.actual = actual;
            }

            public SwapBean getSwap() {
                return swap;
            }

            public void setSwap(SwapBean swap) {
                this.swap = swap;
            }

            public HugepagesBean getHugepages() {
                return hugepages;
            }

            public void setHugepages(HugepagesBean hugepages) {
                this.hugepages = hugepages;
            }

            public long getTotal() {
                return total;
            }

            public void setTotal(long total) {
                this.total = total;
            }

            public UsedBeanXXX getUsed() {
                return used;
            }

            public void setUsed(UsedBeanXXX used) {
                this.used = used;
            }

            public long getFree() {
                return free;
            }

            public void setFree(long free) {
                this.free = free;
            }

            public static class ActualBean {
                /**
                 * free : 6912364544
                 * used : {"bytes":1477447680,"pct":0.1761}
                 */

                private long free;
                private UsedBean used;

                public long getFree() {
                    return free;
                }

                public void setFree(long free) {
                    this.free = free;
                }

                public UsedBean getUsed() {
                    return used;
                }

                public void setUsed(UsedBean used) {
                    this.used = used;
                }

                public static class UsedBean {
                    /**
                     * bytes : 1477447680
                     * pct : 0.1761
                     */

                    private int bytes;
                    private double pct;

                    public int getBytes() {
                        return bytes;
                    }

                    public void setBytes(int bytes) {
                        this.bytes = bytes;
                    }

                    public double getPct() {
                        return pct;
                    }

                    public void setPct(double pct) {
                        this.pct = pct;
                    }
                }
            }

            public static class SwapBean {
                /**
                 * total : 0
                 * used : {"pct":0,"bytes":0}
                 * free : 0
                 */

                private int total;
                private UsedBeanX used;
                private int free;

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public UsedBeanX getUsed() {
                    return used;
                }

                public void setUsed(UsedBeanX used) {
                    this.used = used;
                }

                public int getFree() {
                    return free;
                }

                public void setFree(int free) {
                    this.free = free;
                }

                public static class UsedBeanX {
                    /**
                     * pct : 0
                     * bytes : 0
                     */

                    private int pct;
                    private int bytes;

                    public int getPct() {
                        return pct;
                    }

                    public void setPct(int pct) {
                        this.pct = pct;
                    }

                    public int getBytes() {
                        return bytes;
                    }

                    public void setBytes(int bytes) {
                        this.bytes = bytes;
                    }
                }
            }

            public static class HugepagesBean {
                /**
                 * used : {"bytes":0,"pct":0}
                 * free : 0
                 * reserved : 0
                 * surplus : 0
                 * default_size : 2097152
                 * total : 0
                 */

                private UsedBeanXX used;
                private int free;
                private int reserved;
                private int surplus;
                private int default_size;
                private int total;

                public UsedBeanXX getUsed() {
                    return used;
                }

                public void setUsed(UsedBeanXX used) {
                    this.used = used;
                }

                public int getFree() {
                    return free;
                }

                public void setFree(int free) {
                    this.free = free;
                }

                public int getReserved() {
                    return reserved;
                }

                public void setReserved(int reserved) {
                    this.reserved = reserved;
                }

                public int getSurplus() {
                    return surplus;
                }

                public void setSurplus(int surplus) {
                    this.surplus = surplus;
                }

                public int getDefault_size() {
                    return default_size;
                }

                public void setDefault_size(int default_size) {
                    this.default_size = default_size;
                }

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public static class UsedBeanXX {
                    /**
                     * bytes : 0
                     * pct : 0
                     */

                    private int bytes;
                    private int pct;

                    public int getBytes() {
                        return bytes;
                    }

                    public void setBytes(int bytes) {
                        this.bytes = bytes;
                    }

                    public int getPct() {
                        return pct;
                    }

                    public void setPct(int pct) {
                        this.pct = pct;
                    }
                }
            }

            public static class UsedBeanXXX {
                /**
                 * bytes : 1539088384
                 * pct : 0.1834
                 */

                private int bytes;
                private double pct;

                public int getBytes() {
                    return bytes;
                }

                public void setBytes(int bytes) {
                    this.bytes = bytes;
                }

                public double getPct() {
                    return pct;
                }

                public void setPct(double pct) {
                    this.pct = pct;
                }
            }
        }
    }

    public static class BeatBean {
        /**
         * name : server14sudo
         * hostname : server14sudo
         * version : 6.5.4
         */

        private String name;
        private String hostname;
        private String version;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static class HostBean {
        /**
         * name : server14sudo
         * architecture : x86_64
         * os : {"platform":"centos","version":"6.9 (Final)","family":"redhat","codename":"Final"}
         * containerized : true
         * ip : ["192.168.23.2"]
         * mac : ["00:0c:29:7f:09:ef"]
         */

        private String name;
        private String architecture;
        private OsBean os;
        private boolean containerized;
        private List<String> ip;
        private List<String> mac;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getArchitecture() {
            return architecture;
        }

        public void setArchitecture(String architecture) {
            this.architecture = architecture;
        }

        public OsBean getOs() {
            return os;
        }

        public void setOs(OsBean os) {
            this.os = os;
        }

        public boolean isContainerized() {
            return containerized;
        }

        public void setContainerized(boolean containerized) {
            this.containerized = containerized;
        }

        public List<String> getIp() {
            return ip;
        }

        public void setIp(List<String> ip) {
            this.ip = ip;
        }

        public List<String> getMac() {
            return mac;
        }

        public void setMac(List<String> mac) {
            this.mac = mac;
        }

        public static class OsBean {
            /**
             * platform : centos
             * version : 6.9 (Final)
             * family : redhat
             * codename : Final
             */

            private String platform;
            private String version;
            private String family;
            private String codename;

            public String getPlatform() {
                return platform;
            }

            public void setPlatform(String platform) {
                this.platform = platform;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getFamily() {
                return family;
            }

            public void setFamily(String family) {
                this.family = family;
            }

            public String getCodename() {
                return codename;
            }

            public void setCodename(String codename) {
                this.codename = codename;
            }
        }
    }

    public static class MetricsetBean {
        /**
         * name : memory
         * module : system
         * rtt : 923
         */

        private String name;
        private String module;
        private int rtt;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public int getRtt() {
            return rtt;
        }

        public void setRtt(int rtt) {
            this.rtt = rtt;
        }
    }
}

