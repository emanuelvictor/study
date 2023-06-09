package online.meavalia.ui.criteria.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import online.meavalia.ExecutionActivity;
import online.meavalia.R;
import online.meavalia.databinding.ItemTransformBinding;
import online.meavalia.databinding.ListCriteriaFragmentBinding;
import online.meavalia.domain.model.Criteria;

/**
 * Fragment that demonstrates a responsive layout pattern where the format of the content
 * transforms depending on the size of the screen. Specifically this Fragment shows items in
 * the [RecyclerView] using LinearLayoutManager in a small screen
 * and shows items using GridLayoutManager in a large screen.
 */
public class ListCriteriaFragment extends Fragment {

    private ListCriteriaFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = ListCriteriaFragmentBinding.inflate(inflater, container, false);

        configureTitle();
        configureBackButton();
        configureFabButton();

        final ListAdapter<Criteria, TransformViewHolder> adapter = new TransformAdapter();
        final RecyclerView recyclerView = binding.recyclerviewTransform;
        recyclerView.setAdapter(adapter);

        final ListCriteriaViewModel listCriteriaViewModel =
                new ViewModelProvider(this)
                        .get(ListCriteriaViewModel.class);

        listCriteriaViewModel.getCriterias().observe(getViewLifecycleOwner(), adapter::submitList);
        return binding.getRoot();
    }

    private void configureTitle() {
        Objects.requireNonNull(getMainActivity().getSupportActionBar()).setTitle("List of Criteria");
    }

    private void configureBackButton() {
        Objects.requireNonNull(getMainActivity().getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
    }

    private void configureFabButton() {
        binding.fab.setOnClickListener(view -> getNavController().navigate(R.id.nav_insert_criteria));
    }

    // TODO utilize from mainactivy
    private NavController getNavController() {
        final NavHostFragment navHostFragment =
                (NavHostFragment) getMainActivity()
                        .getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_content_main);
        assert navHostFragment != null;
        return navHostFragment.getNavController();
    }

    private AppCompatActivity getMainActivity() {
        return ((AppCompatActivity) getActivity());
    }

    private void startAssessmentActivity(final Criteria criteria) {
        final Intent intent = new Intent(getMainActivity(), ExecutionActivity.class);
        intent.putExtra("criteria", criteria); //Optional parameters
        this.getMainActivity().startActivity(intent);
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
            final ItemTransformBinding binding = ItemTransformBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new TransformViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull TransformViewHolder holder, int position) {
            holder.criteriaNameTextView.setText(getItem(position).getName());
            holder.sentenceTextView.setText(getItem(position).getSentence());
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

        public TransformViewHolder(final ItemTransformBinding binding) {
            super(binding.getRoot());
            imageView = binding.imageViewItemTransform;
            criteriaNameTextView = binding.criteriaNameTextView;
            sentenceTextView = binding.criteriaSentenceTextView;
            binding.imageViewItemTransform.getRootView().setClickable(true);
            binding.imageViewItemTransform.getRootView().setOnClickListener(v -> {
                startAssessmentActivity(criteria);
            });
        }

        public void setCriteria(Criteria criteria) {
            this.criteria = criteria;
        }
    }
}