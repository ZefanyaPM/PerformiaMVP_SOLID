package performia.service;

import java.util.ArrayList;
import java.util.List;
import performia.repo.IPengumumanRepo;

public class PengumumanService implements IPengumumanService {
    private final IPengumumanRepo pengumumanRepo;

    public PengumumanService(IPengumumanRepo pengumumanRepo) {
        this.pengumumanRepo = pengumumanRepo;
    }

    @Override
    public PengumumanDetail getPengumuman(int index) {
        if (index < 0 || index >= pengumumanRepo.getList().size()) {
            throw new IllegalArgumentException("Pengumuman tidak valid.");
        }

        String isi = pengumumanRepo.getIsiPengumuman(index);
        List<String> lines = new ArrayList<>();
        if (isi != null && !isi.isBlank()) {
            for (String line : isi.split("\\R")) {
                if (!line.isBlank()) {
                    lines.add(line);
                }
            }
        }

        while (lines.size() < 4) {
            lines.add("");
        }

        return new PengumumanDetail(
                pengumumanRepo.getJudulPengumuman(index),
                List.copyOf(lines.subList(0, Math.min(4, lines.size())))
        );
    }

    @Override
    public List<PengumumanDetail> getSemuaPengumuman() {
        List<PengumumanDetail> announcements = new ArrayList<>();
        for (int i = 0; i < pengumumanRepo.getList().size(); i++) {
            announcements.add(getPengumuman(i));
        }
        return announcements;
    }
}
