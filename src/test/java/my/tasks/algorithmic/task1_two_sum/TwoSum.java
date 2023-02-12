package my.tasks.algorithmic.task1_two_sum;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TwoSum {

    @Test
    public void run() {
        int[] nums = {3, 2, 1, 4};
        int[] nums2 = {2, 7, 11, 15};
        int[] nums3 = {3, 3};
        int target = 6;

        int[] twoSum = twoSum(nums, target);
        int[] twoSumOptimized = twoSumOptimized(nums, target);

        int[] twoSum2 = twoSum(nums2, target);
        int[] twoSumOptimized2 = twoSumOptimized(nums2, target);

        int[] twoSum3 = twoSum(nums3, target);
        int[] twoSumOptimized3 = twoSumOptimized(nums3, target);

        log.info("Последовательность " + Arrays.toString(nums));
        log.info(Arrays.toString(twoSum));
        log.info(Arrays.toString(twoSumOptimized));

        log.info("Последовательность " + Arrays.toString(nums2));
        log.info(Arrays.toString(twoSum2));
        log.info(Arrays.toString(twoSumOptimized2));

        log.info("Последовательность " + Arrays.toString(nums3));
        log.info(Arrays.toString(twoSum3));
        log.info(Arrays.toString(twoSumOptimized3));
    }

    // O(N2)
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int currentNumber = nums[i];
            for (int j = 0; j < nums.length; j++) {
                if (currentNumber + nums[j] == target && j != i) {
                    return new int[]{i, j};
                }
            }
        }

        return result;
    }

    // O(N)
    public int[] twoSumOptimized(int[] nums, int target) {
        Map<Integer, Integer> tracker = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (tracker.containsKey(nums[i])) {
                int left = tracker.get(nums[i]);
                return new int[]{left, i};
            } else
                tracker.put(target - nums[i], i);
        }

        return new int[2];
    }
}
