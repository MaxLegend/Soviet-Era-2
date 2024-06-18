package ru.tesmio.soviet.reg;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.List;

// FIXME Icosider: Some kind of unrealistic hardcore
public class Symbols {
    public static RegistryObject<Block> a, b, v, g, d, e, ge, z, i, ia, k, l, m, n, o, p, r, s, t, u, f, h, c, ch, sh, shc, ui, bi, ue, uy, ya, dash;
    public static RegistryObject<Block> one, two, three, four, five, six, seven, eight, nine, zero, num;
    public static List<RegistryObject<Block>> SYMBOLS = new ArrayList<>();
    public static String[] SYMBOLS_NAME = {
            "a", "b", "v", "g", "d", "e", "ge", "z", "i", "ia", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f",
            "h", "c", "ch", "sh", "shc", "ui", "bi", "ue", "uy", "ya", "dash",
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "num"
    };

    public static void addSymbols() {
        SYMBOLS.add(a);
        SYMBOLS.add(b);
        SYMBOLS.add(v);
        SYMBOLS.add(g);
        SYMBOLS.add(d);
        SYMBOLS.add(e);
        SYMBOLS.add(ge);
        SYMBOLS.add(z);
        SYMBOLS.add(i);
        SYMBOLS.add(ia);
        SYMBOLS.add(k);
        SYMBOLS.add(l);
        SYMBOLS.add(m);
        SYMBOLS.add(n);
        SYMBOLS.add(o);
        SYMBOLS.add(p);
        SYMBOLS.add(r);
        SYMBOLS.add(s);
        SYMBOLS.add(t);
        SYMBOLS.add(u);
        SYMBOLS.add(f);
        SYMBOLS.add(h);
        SYMBOLS.add(c);
        SYMBOLS.add(ch);
        SYMBOLS.add(sh);
        SYMBOLS.add(shc);
        SYMBOLS.add(ui);
        SYMBOLS.add(bi);
        SYMBOLS.add(ue);
        SYMBOLS.add(uy);
        SYMBOLS.add(ya);
        SYMBOLS.add(num);
        SYMBOLS.add(dash);
        SYMBOLS.add(zero);
        SYMBOLS.add(one);
        SYMBOLS.add(two);
        SYMBOLS.add(three);
        SYMBOLS.add(four);
        SYMBOLS.add(five);
        SYMBOLS.add(six);
        SYMBOLS.add(seven);
        SYMBOLS.add(eight);
        SYMBOLS.add(nine);
    }
}
