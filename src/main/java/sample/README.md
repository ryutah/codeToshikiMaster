# サンプルコードについて
メールでごちゃごちゃ言ってもいまいち伝わりづらいだろうと思い、私の方でサンプルコード作成してみました  
粂さんのコード自体はそのまま残っていますので、比べてみたりしてみてください。  
個人的な趣味が詰まってますがあまり気にしないでください。  
※IntelliJでコーディングしたので、ディレクトリ構成とか結構変わちゃってます。構成変わるといろいろ面倒だと思いますんで、無理にマージしなくてもダイジョブです。

# Factoryクラスについて
概念的には間違っておりません。  
個人的には、Factoryクラスは以下のような利点があると思っています。

### 1. インスタンスの生成方法に名前をつけられる
コンストラクタとFactoryメソッドの大きな違いとして、インスタンス生成に名前をつけられるということが挙げられます。  
メソッド名だけでどのようなインスタンスが生成されるのかわかるようになりますので、コードの視認性がよくなります。

```java
// コンストラクタでは、どのようなインスタンスが生成されるのかjavadocに書くかコード直接見るかしないとわからない。
Something obj = new Something();
// Factoryメソッドを使うと、メソッド名である程度判断できる
Something obj = SomethingFactory.createAsSomething();
```

### 2. 同じ引数になってしまう場合に対応できる
コンストラクタでは、名前を付けることができないため全く同じ引数のものは定義することはできません。  
ですが、Factoryメソッドの場合は名前を変えれば同一の引数を使用したインスタンス生成が可能となります。
```java
public Something(String foo) {
    ...
}

// 同じ引数のコンストラクタは宣言できない
public Something(String foo) {
    ...
}

// Factoryメソッドの場合
public Something createDefault(String foo) {
   ...
}

// メソッド名を変えることで、同じ引数を取るインスタンス生成が可能
public Something createWithSomething(String foo) {
  ...
}
```

### 3. インスタンス生成の依存を解消できる
少し説明が難しいのですが、コンストラクタを使用したインスタンス生成と比べ依存関係を弱くすることが出来ます。  
ちょっと文章で上手く説明する自信がないので、直接合った時に覚えていたら聞いてください。  
(もしくは平山さんとかに聞いてみてください。**※おすすめ！！**)

# Enumについて
Enumの定義方法は問題ないと思いますが、使用方法に少し変なところがありました。  
具体的には、JudgeBase#judgeJanken(PlayerBase, PlayerBase)の手の比較のところです。  

```java
...
// Enumは定数のように使用できるものですので、わざわざEnumで定義している文字列を使用して
// 比較を行う必要はありません。
if(p1Hand == janken.GU.get() ... {
    ...
}

// このように比較できます。
// ※このようにする場合は、PlayerBaseのdoJunkenの戻り値で、JunkenHandTypeを返すようにする
//修正が必要になります。
if (p1Hand.equals(jankenGU) ... {
    ...
}
...
```

# その他
前回見落としてしまっていたのですが、JudgeBase#judgeJanken(PlayerBase, PlayerBase)で文字列の比較に「==」が使用されています。  
**Javaで文字列を比較する際は「equals」メソッドを使用するようにしてください。**  
「==」を使用した文字列比較は、想定通りに比較できない場合があります。  
具体的には、次のような場合があります。
```java
class Main {
    public static void main(String[] args) {
        String hoge = "hoge";
        String hoge2 = "hoge";
        String hoge3 = new String("hoge");
        System.out.println(hoge == hoge2); // これは「true」
        System.out.println(hoge == hoge3); // これは「false」
    }
}
```
[repl.it](https://repl.it/languages/java)とか使うとさくっと動作確認できるので、暇だったら上のコード試してみてください。
