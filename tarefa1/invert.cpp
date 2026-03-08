# include <iostream>
# include <vector>
# include <string>
# include <algorithm>

/* Esse código é um auxiliar para resolver questões com a arte em ASCII*/

int main() {
    std::vector<std::string> text;
    std::vector<std::string> new_text;

    text.push_back("-=(o`'.  ");
    text.push_back("  '.-.\\  ");
    text.push_back("  /|  \\\\  ");
    text.push_back("  '|  || ");
    text.push_back("   _\\_):,_");

    for (int i = 0; i < 5; i++) 
        std::reverse(text[i].begin(), text[i].end());

    for (std::string x : text) std::cout << x << "\n";
}