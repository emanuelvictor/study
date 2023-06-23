package online.meavalia.ui.criteria.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import online.meavalia.R;
import online.meavalia.databinding.ItemCriteriaBinding;
import online.meavalia.databinding.ListCriteriaFragmentBinding;
import online.meavalia.domain.model.Criteria;
import online.meavalia.ui.generic.AbstractCustomFragmentImpl;

public class ListCriteriaFragment extends AbstractCustomFragmentImpl {

    private OptionsToSelectCriteriaFromList optionsToSelectCriteriaFromList;

    private ListCriteriaFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = ListCriteriaFragmentBinding.inflate(inflater, container, false);

        configureTitle();
        configureBackButton();
        configureFabButton();
        configureListView();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        configureListView();
        super.onResume();
    }

    private void configureListView() {
        final ListAdapter<Criteria, TransformViewHolder> adapter = new TransformAdapter();
        final RecyclerView recyclerView = binding.itemCriteria;
        recyclerView.setAdapter(adapter);

        final ListCriteriaViewModel listCriteriaViewModel =
                new ViewModelProvider(this)
                        .get(ListCriteriaViewModel.class);

        listCriteriaViewModel.getCriterias().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    private void configureTitle() {
        Objects.requireNonNull(getMainActivity().getSupportActionBar()).setTitle(R.string.list_of_criteria);
    }

    private void configureBackButton() {
        Objects.requireNonNull(getMainActivity().getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
    }

    private void configureFabButton() {
        binding.fab.setOnClickListener(view -> {
            getNavController().navigate(R.id.nav_insert_criteria);
            if (Objects.nonNull(this.optionsToSelectCriteriaFromList)) {
                this.optionsToSelectCriteriaFromList.finish();
                this.optionsToSelectCriteriaFromList = null;
            }
        });
    }

    @Override
    public int getNavHostFragmentId() {
        return R.id.nav_host_fragment_content_main;
    }

    private AppCompatActivity getMainActivity() {
        return ((AppCompatActivity) getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class TransformAdapter extends ListAdapter<Criteria, TransformViewHolder> {

        private final List<Integer> drawables = Arrays.asList(
                R.drawable.avatar_1,
                R.drawable.avatar_2,
                R.drawable.avatar_3,
                R.drawable.avatar_4,
                R.drawable.avatar_5,
                R.drawable.avatar_6,
                R.drawable.avatar_7,
                R.drawable.avatar_8,
                R.drawable.avatar_9,
                R.drawable.avatar_10,
                R.drawable.avatar_11,
                R.drawable.avatar_12,
                R.drawable.avatar_13,
                R.drawable.avatar_14,
                R.drawable.avatar_15,
                R.drawable.avatar_16);

        protected TransformAdapter() {
            super(new DiffUtil.ItemCallback<Criteria>() {

                @Override
                public boolean areItemsTheSame(@NonNull final Criteria oldItem, @NonNull final Criteria newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull final Criteria oldItem, @NonNull final Criteria newItem) {
                    return oldItem.equals(newItem);
                }
            });
        }

        @NonNull
        @Override
        public TransformViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
            final ItemCriteriaBinding binding = ItemCriteriaBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new TransformViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull TransformViewHolder holder, int position) {
            holder.criteriaNameTextView.setText(getItem(position).getName());
            holder.sentenceTextView.setText(getItem(position).getSentence());
            holder.averageTextView.setText(String.valueOf(getItem(position).getAvg()));
            if (getItem(position).getAvg() == null)
                holder.averageTextView.setVisibility(View.GONE);
            holder.setCriteria(getItem(position));
            holder.imageView.setImageDrawable(
                    ResourcesCompat.getDrawable(holder.imageView.getResources(),
                            drawables.get(position),
                            null));
        }
    }

    private class TransformViewHolder extends RecyclerView.ViewHolder {

        private Criteria criteria;
        private final ImageView imageView;
        private final TextView criteriaNameTextView;
        private final TextView sentenceTextView;
        private final TextView averageTextView;

        public TransformViewHolder(final ItemCriteriaBinding binding) {
            super(binding.getRoot());
            imageView = binding.imageViewItemTransform;
            criteriaNameTextView = binding.criteriaNameTextView;
            sentenceTextView = binding.criteriaSentenceTextView;
            averageTextView = binding.averageTextView;
            binding.imageViewItemTransform.getRootView().setClickable(true);
            binding.imageViewItemTransform.getRootView().setOnClickListener(v -> {
                /*if (actionMode != null){
                    return false;
                }

                posicaoSelecionada = position;

                view.setBackgroundColor(Color.LTGRAY);

                viewSelecionada = view;

                listViewPessoas.setEnabled(false);

                actionMode = */
                optionsToSelectCriteriaFromList = new OptionsToSelectCriteriaFromList(criteria, getMainActivity());
                getMainActivity().startSupportActionMode(optionsToSelectCriteriaFromList);

//                startAssessmentActivity(criteria);
            });
        }

        public void setCriteria(Criteria criteria) {
            this.criteria = criteria;
        }
    }
}