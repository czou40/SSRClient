package online.xiaoshao.ssrclient;

public class NodeData {
    private String remarks;
    private String server;
    private long server_port;
    private String method;
    private String obfs;
    private String obfsparam;
    private String remarks_base64;
    private String password;
    private boolean tcp_over_udp;
    private boolean udp_over_tcp;
    private String group;
    private String protocol;
    private boolean obfs_udp;
    private boolean enable;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public long getServer_port() {
        return server_port;
    }

    public void setServer_port(long server_port) {
        this.server_port = server_port;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getObfs() {
        return obfs;
    }

    public void setObfs(String obfs) {
        this.obfs = obfs;
    }

    public String getObfsparam() {
        return obfsparam;
    }

    public void setObfsparam(String obfsparam) {
        this.obfsparam = obfsparam;
    }

    public String getRemarks_base64() {
        return remarks_base64;
    }

    public void setRemarks_base64(String remarks_base64) {
        this.remarks_base64 = remarks_base64;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTcp_over_udp() {
        return tcp_over_udp;
    }

    public void setTcp_over_udp(boolean tcp_over_udp) {
        this.tcp_over_udp = tcp_over_udp;
    }

    public boolean isUdp_over_tcp() {
        return udp_over_tcp;
    }

    public void setUdp_over_tcp(boolean udp_over_tcp) {
        this.udp_over_tcp = udp_over_tcp;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public boolean isObfs_udp() {
        return obfs_udp;
    }

    public void setObfs_udp(boolean obfs_udp) {
        this.obfs_udp = obfs_udp;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
