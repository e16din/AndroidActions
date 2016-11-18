# AndroidActions
AndroidActions - плагин для автоматизации рутинных действий при разработке android-приложений.

## Action: Generate Views from Layout:

1. Откройте необходимый layout
2. Нажмите [SHIFT + CMD(CTRL) + A]
3. Введите "Generate Views from Layout"

Используя это действие вы сгенерируете в буфер обмена набор переменных вида "Тип id;"

Например из такого layout: 
```xml
<TextView
        android:id="@+id/vLoginLabel"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
<EditText
        android:id="@+id/vLoginField"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
<Button
        android:id="@+id/vLoginButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/> 
```
мы получим строку:
```java
TextView vLoginLabel;
EditText vLoginField;
Button vLoginButton;
```

Удобно при формировании холдеров, activity, и прочих классов где используются view.

### История:
Раньше в качестве id для view я указывал имена в нижнем регистре, к примеру "btn_done", этого требовали правила построения xml-документов и IDE Eclipse.
Сейчас IDE Android Studio не ограничивает нас в именовании id и можно использовать CamelCase. 

В ходе разработки десятков мобильных приложений, я выработал для себя такие правила именования view-переменных:

#### Использовать префикс "v" для каждого view:

```java
View vLoginLabel;
boolean mAuthorized;
```
    
* Это дает быстрый доступ ко всем view при наборе кода;
* Позволяет читая код, легко распознавать view-переменные и все остальные;


#### Использовать одинаковые названия для id и переменной:
```java
TextView vLoginLabel;
```
```xml
<TextView
        android:id="@+id/vLoginLabel"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```
        
Мы можем это сделать, так как название id мы используем только для определения переменной.

Это дает ряд удобств:       

* У нас вдвое меньше сущностей перед глазами, а значит меньше путаницы и ими легче оперировать;
* Задав один раз название переменной, мы используем его же для id и наоборот;

```java
vLoginLabel = findViewById(R.id.vLoginLabel); 
```


#### Использовать постфикс указывающий на функциональность view:

```java
TextView vLoginLabel; //текст над полем логина
EditText vLoginField; //поле для ввода логина
EditText vPasswordField; //поле для ввода пароля

AnyCustomButton vLoginButton; //кнопка логина
AnyCustomImage vLogoImage; //логотип на экране логина


LinearLayout vAuthFieldsContainer; //контейнер содержащий поля авторизации (логин, пароль)
```

В примере показаны постфиксы "Label", "Field", "Button", "Image", "Container"
Они используются наиболее часто на моей практике.

Постфиксы в названии переменной дают следующие удобства:

* Их легко запоминать и удобно читать, они несут смысловую нагрузку (vLoginButton понятнее чем acbLogin и mLogin);
* Так как постфиксы не привязаны к типу view, мы без зазрения совести, можем сменить тип view оставив название переменной прежним 
(например ImageView vLoginButton);

<b>Стандартизация именования, в целом, дает нам возможность легче ориентироваться в собственном и чужем коде, а также открывает путь к кодогенерации и автоматизации рутинных действий.</b>

# Присоединяйтесь! Заводите issues, делайте pull request'ы, пишите замечания и предложения.


# License MIT
Copyright (c) 2016 [Александр Кундрюков (e16din)](http://goo.gl/pzjc8x)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.