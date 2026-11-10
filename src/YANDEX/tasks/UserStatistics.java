package YANDEX.tasks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class UserStatistics {
    /*
    Есть последовательность запросов пользователей,
    каждый запрос - это пара (time, userId),
    запросы всегда приходят в отсортированном по времени порядке.
    Периодически спрашивают: сколько уникальных пользователей за окно [now - k, now] сделали >= limit запросов.
    пользователей, которые задали >= limit запросов". k > 0, limit > 0
    Есть поток событий (time, userId), приходящих в порядке возрастания времени.
    event и robotCount вызываются в порядке возрастания now.

    Идея:
    поддерживать скользящее окно по времени
    и для каждого пользователя — счётчик запросов внутри окна.
    Плюс отдельно отслеживать, у кого этот счётчик >= limit.
    Три структуры данных:
    Deque<long[]> events — очередь всех событий в окне, чтобы выкидывать старые. Хранит {time, userId}.
    HashMap<Long, Integer> count — счётчик запросов каждого пользователя в текущем окне.
    Счётчик robots — сколько пользователей сейчас имеют count >= limit.

    Сложность O(1) амортизированно:
    Один раз добавляем, 1 раз удаляем, HashMap всё по O(1)
    return robotCount - возвращаем за O(1) + очистка.
    */
    private final Deque<long[]> events;
    private final HashMap<Long, Integer> count;
    private final long k;
    private final int limit;
    private int robots;
    public UserStatistics(long k, int limit) {
        this.k = k;
        this.limit = limit;
        this.events = new ArrayDeque<>();
        this.count = new HashMap<>();
        this.robots = 0;
    }

    public void event(long now, long userId) {
        // Добавляем событие в конец очереди
        // Порядко сохраняем, так как идём по now
        events.addLast(new long[]{now, userId});
        //Увеличиваем счётчик
        int c = count.getOrDefault(userId, 0) + 1;
        count.put(userId, c);
        // увеличиваем кол-во, если достигли лимита
        if (c == limit) robots++;
    }
    //Сколько роботов в окне [now - k, now]
    public int robotCount(long now) {
        // Граница
        long cutoff = now - k;
        // Проходимся по деке, всё что раньше cutoff - выкидываем
        while (!events.isEmpty() && events.peekFirst()[0] < cutoff) {
            //Достаём событие
            long[] event = events.pollFirst();
            // Достаём id
            long user = event[1];
            // Уменьшаем счётчик пользователя
            int c = count.get(user) - 1;
            // Если кол-во пользователей 0 - удаляем из мапы
            // Иначе обновляем значение в мапе
            if (c == 0) {
                count.remove(user);
            } else {
                count.put(user, c);
            }
            // Если кол-во запросов пользователя == limit - 1,
            // пользователь не входит в лимиты
            if (c == limit - 1) robots--;
        }
        return robots;
    }
}
