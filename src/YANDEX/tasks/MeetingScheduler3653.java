package YANDEX.tasks;

import java.util.List;

public class MeetingScheduler3653 {

    public static class Interval{
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * https://www.lintcode.com/problem/3653/description
     * Даны два списка массивов интервалов slot1, slot2 и время duration.
     * Верните значение раннего интервала, когда slot1 и slot2 пересекаются не менее duration.
     * Если таких нет, вернуть [-1,-1]
     *
     * slots1 = [(10,50),(60,120),(140,210)] slots2 = [(0,15),(60,70)] duration = 8
     * Output
     * (60,68)
     *
     * slots1 = [(10,50),(60,120),(140,210)] slots2 = [(0,15),(60,70)] duration = 12
     * Output
     * (-1,-1)
     *
     * Идея: Сортировка + 2 указателя
     * Так как нам не гарантируется порядок, мы обязаны отсортировать в возрастающем порядке
     *
     * Ставим два указателя на начало: i = 0, j = 0.
     * Пока указ < длины списка:
     *  endInt - max от начала i-го и j-го интервала
     *  startInt - min от конца i-го и j-го интервала
     * Таким образом находим пересечение между интервалами.
     * endInt - startInt -> общий интервал пересечения.
     * Если endInt - startInt >= duration: нашли нужное пересечение - вернём его (startInt, startInt + duration);
     *
     * Если не вышли раньше, значит не нашли нужное пересечение: (-1, -1)
     *
     * Сложность:
     *  по времени: O(n log(n) + m log(m))
     *  по памяти: O(1)
     *
     * @param slots1 List<Interval>
     * @param slots2 List<Interval>
     * @param duration int
     */
    public Interval earliestAppropriateDuration(List<Interval> slots1, List<Interval> slots2, int duration) {
        slots1.sort((a, b) -> Integer.compare(a.start, b.start));
        slots2.sort((a, b) -> Integer.compare(a.start, b.start));
        int i = 0;
        int j = 0;
        while (i < slots1.size() && j < slots2.size()) {
            int startInt = Math.max(slots1.get(i).start, slots2.get(j).start);
            int endInt = Math.min(slots1.get(i).end, slots2.get(j).end);
            if (endInt - startInt >= duration) return new Interval(startInt, startInt + duration);

            if (slots1.get(i).end > slots2.get(j).end) j++;
            else i++;
        }
        return new Interval(-1,-1);
    }
}
