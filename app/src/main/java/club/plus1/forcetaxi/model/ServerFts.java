package club.plus1.forcetaxi.model;

import club.plus1.forcetaxi.stub.ConstantStub;

public class ServerFts {
    public String tin;
    private String oktmo;
    private String dateFNS;

    public ServerFts(String tin) {
        this.tin = tin;
        oktmo = ConstantStub.OKTMO;
        dateFNS = ConstantStub.DATE_FNS;
    }
}
