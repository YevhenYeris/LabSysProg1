package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * 9. Знайти ті слова які мають
 * найбільшу кількість різних літер,
 * тобто літери, що повторюються у
 * слові, не враховувати.
 * */

public class Main {

    public static void main(String[] args) {
        try {
            //Відкриємо текстовий файл
            File file = new File("source.txt");
            Scanner myReader = new Scanner(file);

            //Колекція для зберігання слів із вхідного файлу
            ArrayList<String> words = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                //Розбиваємо рядок на слова. Роздільник - будь-який
                //символ, окрім букв або цифр
                String[] line = data.split("[^a-zA-Z0-9]");
                shorten(line);
                words.addAll(Arrays.asList(line));
            }
            myReader.close();

            //Знайдемо слова, що задовольняють
            //умову роботи
            ArrayList<String> res = getResult(words);
            for (String word : res)
            {
                System.out.print(word + " ");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено");
            e.printStackTrace();
        }
    }

    //Функція пошуку слів за умовою роботи
    private static ArrayList<String> getResult(ArrayList<String> words)
    {
        ArrayList<String> res = new ArrayList<>();
        int max = 0;

        for (String word : words)
        {
            int charsCount = (int)word.toUpperCase(Locale.ROOT).chars().distinct().count();

            if (charsCount == max)
            {
                res.add(word);
            }
            if (charsCount > max)
            {
                max = charsCount;
                res.clear();
                res.add(word);
            }
        }
        return  res;
    }

    //Функція скорочення слів масиву
    private static  void shorten(String[] words)
    {
        for (int i = 0; i < words.length; ++i)
        {
            words[i] = words[i].length() > 30 ? words[i].substring(0, 30) : words[i];
        }
    }
}
