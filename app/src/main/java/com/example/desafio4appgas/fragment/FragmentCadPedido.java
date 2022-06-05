package com.example.desafio4appgas.fragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.desafio4appgas.R;
import com.example.desafio4appgas.dao.PagamentoRepository;
import com.example.desafio4appgas.dao.PedidoRepository;
import com.example.desafio4appgas.dao.ProdutoRepository;
import com.example.desafio4appgas.model.Produto;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class FragmentCadPedido extends Fragment {
        private TextInputEditText txtNome;
        private TextInputEditText txtCpf;
        private Button btnCadPedido;
        private TextInputEditText txtQuantidade;
        private TextInputEditText txtPagamento;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_cad_pedido, container, false);
            Bundle bundle = getArguments();
            String nome = bundle.getString("NOME");
            String cpfUsuario = bundle.getString("CPF");
            txtNome = root.findViewById(R.id.txtNome);
            txtNome.setText(nome);
            txtCpf = root.findViewById(R.id.txtCpf);
            txtCpf.setText(cpfUsuario);
            txtPagamento = root.findViewById(R.id.txtPagamento);
            txtQuantidade = root.findViewById(R.id.txtQuantidade);
            btnCadPedido = root.findViewById(R.id.btnCadPedido);
            btnCadPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inserirPedido();
                }
            });
            return root;
    }

    private void inserirPedido() {
            long idPedido = 0;
            PagamentoRepository pagamentoRepository = new PagamentoRepository(getActivity().getBaseContext());
            long idPagamento = pagamentoRepository.insert(txtPagamento.getText().toString(), 1);
            ProdutoRepository produtoRepository = new ProdutoRepository(getActivity().getBaseContext());
            Produto p = new Produto();
            p = produtoRepository.getProduto(txtNome.getText().toString());
            if (idPagamento !=-1 && p.getIdProduto() > 0) {
                    PedidoRepository pedidoRepository = new PedidoRepository(getActivity().getBaseContext());
                    idPedido = pedidoRepository.insert(idPagamento, p.getIdProduto(), Long.parseLong(txtQuantidade.getText().toString()), txtCpf.getText().toString());
            }
            if (idPedido !=-1)
                Snackbar.make(getView(), "Pedido nº. " + idPedido, Snackbar.LENGTH_LONG).show();
                Navigation.findNavController(getView()).navigate(R.id.nav_fragmentListPedido);

    }

}