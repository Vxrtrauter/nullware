package com.Vxrtrauter.NullWare.helper;

import java.util.LinkedList;
import java.util.Queue;

public class TpsHelper {

    private static final int SAMPLE_SIZE = 5; // Matches the 5-sample average in the Kotlin version
    private static final Queue<Long> timeUpdateTimeList = new LinkedList<>();
    private static long lastPacketTime = -1;

    public static double tps = -1.0;

    public static void updateTps() {
        long currentTime = System.currentTimeMillis();

        if (lastPacketTime != -1) {
            long timeSinceLastPacket = Math.max(1000L, currentTime - lastPacketTime);
            timeUpdateTimeList.add(timeSinceLastPacket);

            if (timeUpdateTimeList.size() > SAMPLE_SIZE) {
                timeUpdateTimeList.poll();
            }

            double averageTickTime = timeUpdateTimeList.stream()
                    .mapToDouble(Long::doubleValue)
                    .average()
                    .orElse(1000.0);

            tps = 20000.0 / averageTickTime;
        }

        lastPacketTime = currentTime;
    }
}
