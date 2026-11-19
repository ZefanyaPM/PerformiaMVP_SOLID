package performia.service;

import java.util.List;

import performia.repo.IManajerRepo;
import performia.user.Manajer;

public class ManajerService implements IManajerService {
    private final IManajerRepo manajerRepo;

    public ManajerService(IManajerRepo manajerRepo) {
        this.manajerRepo = manajerRepo;
    }

    @Override
    public String getNamaManajer(int manajerIndex) {
        List<Manajer> listManajer = manajerRepo.getList();
        if (manajerIndex < 0 || manajerIndex >= listManajer.size()) {
            throw new IllegalArgumentException("Manajer tidak valid.");
        }
        return listManajer.get(manajerIndex).getNama();
    }
}
