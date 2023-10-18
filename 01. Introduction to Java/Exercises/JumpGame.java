package intro_to_java;

public class JumpGame {

    public static boolean canWin(int[] arr) {
        return canJumpFromIndex(0, arr);
    }

    private static boolean canJumpFromIndex(int index, int[] arr) {
        if (index == arr.length - 1) {
            return true;
        }

        int maxJump = Math.min(index + arr[index], arr.length - 1);

        for (int nextPosition = index + 1; nextPosition <= maxJump; nextPosition++) {
            if (canJumpFromIndex(nextPosition, arr)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        System.out.println(canWin(new int[]{2, 3, 1, 1, 0}));
        System.out.println(canWin(new int[]{3, 2, 1, 0, 0}));
    }
}
