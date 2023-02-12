Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]

Constraints:

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.

Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?

Решение:
int[] nums = {3, 2, 1, 4};
int target = 6;
Map: 3-0, 4-1, 5-2, 2-3
В мапе храним разницу "target - текущее значение : позиция элемента" - это первой возможное слагаемое.
Ключевой момент, разница нужна, чтобы понять, что если текущий элемент содержится в мапе,
значит его "слагаемое" уже было пройдено и это наш случай
