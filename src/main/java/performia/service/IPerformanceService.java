package performia.service;

import performia.model.Performance;

public interface IPerformanceService {
    String STATUS_WAITING_FOR_REVIEW = "Waiting for Review";
    String STATUS_DONE = "Done";

    Performance hitungPerformance(int karyawanIndex);
}
