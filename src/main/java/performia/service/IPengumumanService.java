package performia.service;

import java.util.List;

public interface IPengumumanService {
    PengumumanDetail getPengumuman(int index);

    List<PengumumanDetail> getSemuaPengumuman();
}
